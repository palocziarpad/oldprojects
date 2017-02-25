package com.snakegame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class
 *
 */
public class Util {
    private static Logger logger = LoggerFactory.getLogger(Util.class);

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

    /***
     * Sleep
     * 
     * @param millisec
     */
    public static void sleep(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
