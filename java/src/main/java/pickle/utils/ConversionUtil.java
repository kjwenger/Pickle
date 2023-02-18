package pickle.utils;

public final class ConversionUtil {

    private ConversionUtil() {}

    public static boolean toBoolean(final String string) {
        return Boolean.TRUE.toString().equals(
                        string.toLowerCase())
                || Integer.valueOf(1).toString().equals(
                        string.toLowerCase());
    }

    public static String toString(final Object object) {
        if (object instanceof Byte)
            return HexUtil.byteToHexString((Byte) object);
        if (object instanceof Byte[])
            return HexUtil.bytesToHexString((Byte[]) object);
        if (object instanceof byte[])
            return HexUtil.bytesToHexString((byte[]) object);
        return String.valueOf(object);
    }

    public static Object toObject(
            final Class<?> forClass, final String string) {
        if (forClass == null)
            return string;
        else if (Boolean.class.isAssignableFrom(forClass))
            return Boolean.valueOf(string);
        else if (Byte.class.isAssignableFrom(forClass))
            return HexUtil.hexStringToByte(string);
        else if (byte[].class.isAssignableFrom(forClass))
            return HexUtil.hexStringToBytes(string);
        else if (Double.class.isAssignableFrom(forClass))
            return Double.valueOf(string);
        else if (Float.class.isAssignableFrom(forClass))
            return Float.valueOf(string);
        else if (Integer.class.isAssignableFrom(forClass))
            return Integer.valueOf(string);
        else if (Long.class.isAssignableFrom(forClass))
            return Long.valueOf(string);
        else
            return string;
    }

}
