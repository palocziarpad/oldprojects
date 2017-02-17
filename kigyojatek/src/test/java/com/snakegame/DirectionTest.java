package com.snakegame;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snakegame.Direction;

public class DirectionTest {
    @Test
    public void testOppositeDirectionAllValuesHasOpposite() {
	// GIVEN
	for (Direction direction : Direction.values()) {
	    // WHEN
	    Direction dir = direction.getOppositeDirection();
	    // THEN
	    Assert.assertNotNull(dir, "The " + direction.name() + " has no opposite value!");
	}
    }
}
