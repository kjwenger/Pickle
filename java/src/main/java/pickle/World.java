package pickle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.util.Pair;

public class World {

    private final static class Key
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

        public Class<?> forClass() {
            return getKey();
        }

        public String forName() {
            return getValue();
        }

        @Override
        public int compareTo(final Key key) {
            int comparedClass =
                    compareClass(key != null ? key.forClass() : null);
            if (comparedClass != 0) return comparedClass;
            return compareName(key != null ? key.forName() : null);
        }

        private int compareClass(final Class<?> forClass) {
            if (forClass() == null && forClass == null) return 0;
            if (forClass() != null && forClass == null) return -1;
            return forClass().getName().compareTo(forClass.getName());
        }

        private int compareName(final String forName) {
            if (forName() == null && forName == null) return 0;
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
        final CLASS object = (CLASS) objectMap.get(new Key(forClass));
        if (object != null) return object;
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forClass.isAssignableFrom(mapEntry.getKey().forClass()))
                return (CLASS) mapEntry.getValue();
        return null;
    }

    @SuppressWarnings("unchecked")
    public <CLASS> CLASS getObject(Class<CLASS> forClass, String forName) {
        final CLASS object = (CLASS) objectMap.get(new Key(forClass, forName));
        if (object != null) return object;
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forClass.isAssignableFrom(mapEntry.getKey().forClass()))
                return (CLASS) mapEntry.getValue();
        return null;
    }

    @SuppressWarnings("unchecked")
    public <CLASS> List<CLASS> getObjects(final Class<CLASS> forClass) {
        final List<CLASS> objects = new ArrayList<>();
        for (Map.Entry<Key, Object> mapEntry : objectMap.entrySet())
            if (forClass.isAssignableFrom(mapEntry.getKey().forClass()))
                objects.add((CLASS) mapEntry.getValue());
        return objects;
    }

    public <CLASS> void putObject(
            final Class<CLASS> forClass, final CLASS object) {
        objectMap.put(new Key(forClass), object);
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

    public <CLASS> void putObject(
            final Class<CLASS> forClass, final String forName,
            final CLASS object) {
        objectMap.put(new Key(forClass, forName), object);
    }

    public <CLASS> void removeObject(final Class<CLASS> forClass) {
        objectMap.remove(new Key(forClass));
    }

    public <CLASS> void removeObject(
            final Class<CLASS> forClass, final String forName) {
        objectMap.remove(new Key(forClass, forName));
    }

}
