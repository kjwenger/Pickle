package pickle.utils;

public class HexUtil {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static byte hexStringToByte(final String string) {
        byte[] bytes = hexStringToBytes(string);
        return bytes.length > 0 ? bytes[0] : 0;
    }

    public static byte[] hexStringToBytes(final String string) {
        final int length = string.length();
        final String hexString = ((length % 2) != 0)
                ? "0" + string
                : string;
        final int size = (length + 1) / 2;
        final byte[] data = new byte[size];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }


    /**
     * @see <a href="https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java">How to convert a byte array to a hex string in Java?</a>
     */
    public static String bytesToHexString(final byte[] bytes) {
        final char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String bytesToHexString(final Byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String byteToHexString(final byte b) {
        char[] hexChars = new char[2];
        int v = b & 0xFF;
        hexChars[0] = HEX_ARRAY[v >>> 4];
        hexChars[1] = HEX_ARRAY[v & 0x0F];
        return new String(hexChars);
    }

}
