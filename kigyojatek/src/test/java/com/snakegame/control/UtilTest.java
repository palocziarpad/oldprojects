package com.snakegame.control;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilTest {
    @Test
    public void testRandom() {
        // Given
        byte from = 8;
        byte to = 10;
        // When
        for (int i = 0; i < 10; i++) {
            byte actual = Util.random(from, to);
            // Then
            Assert.assertTrue(actual <= to, "The actual value: " + actual + " greater than " + to);
            Assert.assertTrue(actual >= from, "The actual value: " + actual + " greater than " + from);
        }

    }
}
