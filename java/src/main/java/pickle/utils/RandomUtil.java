package pickle.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private RandomUtil() {}

    public static byte randomByte() {
        int randomInt = randomInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
        return (byte) randomInt;
    }

    public static int randomInt() {
        return randomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int randomInt(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}
