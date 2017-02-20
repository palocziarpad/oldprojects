package com.snakegame;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DirectionToPictureTest {
    @Test
    public void testHeadEveryDirectionHasValue() {
	// GIVEN
	for (Direction direction : Direction.values()) {
	    // WHEN
	    PictureFiles actual = DirectionToPicture.getPictureFromHeadDirection(direction);
	    // THEN
	    Assert.assertNotNull(actual, "The actual is null, even tough there was a value given");

	}

    }

    @Test
    public void testHeadDownValue() {
	// GIVEN
	Direction direction = Direction.DOWN;
	// WHEN
	PictureFiles actual = DirectionToPicture.getPictureFromHeadDirection(direction);
	// THEN
	Assert.assertEquals(actual, PictureFiles.HEADDOWN, "The actual is not having the correct value!");

    }

    @Test
    public void testBodyEveryDirectionHasValue() {
	// GIVEN
	for (Direction direction : Direction.values()) {
	    // WHEN
	    PictureFiles actual = DirectionToPicture.getPictureFromBodyDirection(direction);
	    // THEN
	    Assert.assertNotNull(actual, "The actual is null, even tough there was a value given");

	}

    }

    @Test
    public void testBodyDownValue() {
	// GIVEN
	Direction direction = Direction.DOWN;
	// WHEN
	PictureFiles actual = DirectionToPicture.getPictureFromBodyDirection(direction);
	// THEN
	Assert.assertEquals(actual, PictureFiles.BODY_VERTICAL, "The actual is not having the correct value!");

    }

    @Test
    public void testTailEveryDirectionHasValue() {
	// GIVEN
	for (Direction direction : Direction.values()) {
	    // WHEN
	    PictureFiles actual = DirectionToPicture.getPictureFromTailDirection(direction);
	    // THEN
	    Assert.assertNotNull(actual, "The actual is null, even tough there was a value given");

	}

    }

    @Test
    public void testTailDownValue() {
	// GIVEN
	Direction direction = Direction.DOWN;
	// WHEN
	PictureFiles actual = DirectionToPicture.getPictureFromTailDirection(direction);
	// THEN
	Assert.assertEquals(actual, PictureFiles.TAILDOWN, "The actual is not having the correct value!");

    }
}
