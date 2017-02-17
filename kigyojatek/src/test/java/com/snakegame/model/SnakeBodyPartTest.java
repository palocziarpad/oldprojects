package com.snakegame.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SnakeBodyPartTest {
    @Test
    public void testIsAtTrue() {
	// GIVEN
	byte xCoordinate;
	byte yCoordinate = xCoordinate = (byte) 15;
	SnakeBodyPart part = new SnakeBodyPart(xCoordinate, yCoordinate);
	SnakeBodyPart partOther = new SnakeBodyPart(xCoordinate, yCoordinate);
	// WHEN
	boolean actual = part.isAt(partOther);
	// THEN
	Assert.assertTrue(actual, "The coordinates are the same, but still the method gives back false");
    }

    @Test
    public void testIsAtFalse() {
	// GIVEN
	byte xCoordinate = (byte) 14;
	byte yCoordinate = (byte) 15;
	SnakeBodyPart part = new SnakeBodyPart(xCoordinate, yCoordinate);
	SnakeBodyPart partOther = new SnakeBodyPart(xCoordinate, xCoordinate);
	// WHEN
	boolean actual = part.isAt(partOther);
	// THEN
	Assert.assertFalse(actual, "The coordinates are not the same, but still the method gives back true");
    }
}
