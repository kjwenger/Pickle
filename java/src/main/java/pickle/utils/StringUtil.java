package pickle.utils;

import java.nio.charset.Charset;
import java.util.Random;

import static pickle.constants.StringConstants.*;

public final class StringUtil {

    private StringUtil() {}

    public static String randomString(
            final byte length, final String charsetName) {
        int unsignedLength = ((int) length) & 0xFF;
        byte[] bytes = new byte[unsignedLength];
        new Random().nextBytes(bytes);
        return new String(bytes, Charset.forName(charsetName));
    }

    public static String substituteStringLiteralCR(String string) {
        return string.replaceAll(_CR_, "\r");
    }

    public static String substituteStringLiteralLF(String string) {
        return string.replaceAll(_LF_, "\n");
    }

    public static String substituteStringLiteralEOF(String string) {
        return string.replaceAll(_EOF_, "\u0005");
    }

    public static String substituteStringLiteralSPACE(String string) {
        return string.replaceAll(_SPACE_, " ");
    }

    public static String substituteStringLiterals(String string) {
        string = substituteStringLiteralCR(string);
        string = substituteStringLiteralLF(string);
        string = substituteStringLiteralEOF(string);
        string = substituteStringLiteralSPACE(string);
        return _NULL_.equals(string)
                ? null
                : _EMPTY_.equals(string)
                        ? ""
                        : string;
    }

    public static Object evaluateString(final String expression) {
        if (Boolean.TRUE.toString().equals(expression))
            return Boolean.TRUE;
        if (Boolean.FALSE.toString().equals(expression))
            return Boolean.FALSE;
        return substituteStringLiterals(expression);
    }

}
