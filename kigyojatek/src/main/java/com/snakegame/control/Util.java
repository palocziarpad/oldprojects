package com.snakegame.control;

/**
 * Utility class
 *
 */
public class Util {
    /**
     * Create random number within the boundaries
     * 
     * @param minimum
     *            the minimum value it could return including it
     * @param maximum
     *            the maximum value it could return including it
     * @return the random number within the [minimum,maximum] domain
     */
    public static byte random(byte minimum, byte maximum) {
        return (byte) ((Math.random() * (maximum - minimum)) + minimum);
    }
}
