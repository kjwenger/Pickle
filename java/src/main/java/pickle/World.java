package pickle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

import static pickle.constants.StringConstants.AT;

public class World {

    public final static class Key
            extends Pair<Class<?>, String>
            implements Comparable<Key> {
        public Key(final Class<?> forClass, final String forName) {
            super(forClass, forName);
        }

        public Key(final Class<?> forClass) {
            super(forClass, null);
        }

        public Key(final String forName) {
            super(null, forName);
        }

        public Key() {
            super(null, null);
        }

        public static Key forString(final String keyString)
                throws ClassNotFoundException {
            if (keyString == null) return null;
            if (keyString.equals(AT)) return new Key();
            final String regex = "([\\w.$]+)@([\\w_]*)";
            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(keyString);
            if (!matcher.find()) return new Key(keyString);
            final String forClassName = matcher.group(1);
            final String forName = matcher.group(2);
            Class<?> forClass;
            try {
                forClass = Class.forName(forClassName);
            } catch (ClassNotFoundException e) {
                forClass = Class.forName("java.lang." + forClassName);
            }
            if (forName.isBlank() || forName.isEmpty())
                return new Key(forClass);
            return new Key(forClass, forName);
        }

        public Class<?> forClass() {
            return getKey();
        }

        public String forName() {
            return getValue();
        }

        @Override
        public int compareTo(final Key key) {
            final int compared =
                    compareClass(key != null ? key.forClass() : null);
            if (compared != 0) return compared;
            return compareName(key != null ? key.forName() : null);
        }

        private int compareClass(final Class<?> forClass) {
            if (forClass() == null && forClass == null) return 0;
            if (forClass() == null && forClass != null) return 1;
            if (forClass() != null && forClass == null) return -1;
            return forClass().getName().compareTo(forClass.getName());
        }

        private int compareName(final String forName) {
            if (forName() == null && forName == null) return 0;
            if (forName() == null && forName != null) return 1;
            if (forName() != null && forName == null) return -1;
            return forName().compareTo(forName);
        }
    }

    private final Map<Key, Object> objectMap;

    public World() {
        objectMap = new TreeMap<>();
    }

    @SuppressWarnings("unchecked")
    public <CLASS> boolean hasObject(final Class<CLASS> forClass) {
        final CLASS object = (CLASS) objectMap.get(new Key(forClass));
        if (object != null) return true;
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forClass.isAssignableFrom(mapEntry.getKey().forClass()))
                return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public <CLASS> CLASS getObject(final Class<CLASS> forClass) {
        if (forClass == null) return null;
        final CLASS object = (CLASS) objectMap.get(new Key(forClass));
        if (object != null) return object;
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (mapEntry.getKey().forClass() != null)
                if (forClass.isAssignableFrom(mapEntry.getKey().forClass()))
                    return (CLASS) mapEntry.getValue();
        return null;
    }

    @SuppressWarnings("unchecked")
    public <CLASS> CLASS getObject(
            final Class<CLASS> forClass, final String forName) {
        final Key key = new Key(forClass, forName);
        return (CLASS) getObject(key);
    }

    @SuppressWarnings("unchecked")
    public <CLASS> CLASS getObject(final String forName) {
        final Key key = new Key(forName);
        final CLASS object = (CLASS) objectMap.get(key);
        if (object != null) return object;
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forName.equals(mapEntry.getKey().forName()))
                return (CLASS) mapEntry.getValue();
        return null;
    }

    @SuppressWarnings("UnnecessaryContinue")
    public Object getObject(final Key key) {
        if (key == null) return null;
        final String forName = key.forName();
        final Class<?> forClass = key.forClass();
        final Object object = objectMap.get(key);
        if (object != null) return object;
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forName != null)
                if (forName.equals(mapEntry.getKey().forName()))
                    if (forClass == null)
                        return mapEntry.getValue();
                    else if (mapEntry.getKey().forClass() != null)
                        if (forClass.isAssignableFrom(
                                mapEntry.getKey().forClass()))
                            return mapEntry.getValue();
                        else continue;
                    else continue;
                else continue;
            else if (forClass != null)
                return getObject(forClass);
        return null;
    }

    public <CLASS> CLASS lazyGetObject(
            final Class<CLASS> forClass, final String forName)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        CLASS object = getObject(forClass, forName);
        if (object == null) {
            final Constructor<CLASS> constructor = forClass.getConstructor();
            object = constructor.newInstance();
            putObject(forClass, forName, object);
        }
        return object;
    }

    @SuppressWarnings("unchecked")
    public <CLASS> List<CLASS> getObjects(final Class<CLASS> forClass) {
        final List<CLASS> objects = new ArrayList<>();
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forClass.isAssignableFrom(mapEntry.getKey().forClass()))
                objects.add((CLASS) mapEntry.getValue());
        return objects;
    }

    public void putObject(final Key key, final Object object) {
        objectMap.put(key, object);
    }

    public <CLASS> void putObject(
            final Class<CLASS> forClass, final CLASS object) {
        objectMap.put(new Key(forClass), object);
    }

    public <CLASS> void putObject(
            final Class<CLASS> forClass, final String forName,
            final CLASS object) {
        objectMap.put(new Key(forClass, forName), object);
    }

    public <CLASS> void putObject(
            final String forName,
            final CLASS object) {
        objectMap.put(new Key(null, forName), object);
    }

    public <CLASS> void removeObject(final Class<CLASS> forClass) {
        objectMap.remove(new Key(forClass));
    }

    public <CLASS> void removeObject(
            final Class<CLASS> forClass, final String forName) {
        objectMap.remove(new Key(forClass, forName));
    }

    public <CLASS> void removeObject(final CLASS object) {
        if (object == null) return;
        final Iterator<Map.Entry<Key, Object>> iterator =
                objectMap.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Key, Object> entry = iterator.next();
            if (object.equals(entry.getValue()))
                iterator.remove();
        }
    }

}
