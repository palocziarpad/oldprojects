package com.snakegame.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snakegame.PictureFiles;
import com.snakegame.view.Board;

public class BoardTest {
    @Test
    public void testNoSlam() {
        // GIVEN
        SnakeBodyPart before = new SnakeBodyPart((byte) 15, (byte) 16);
        SnakeBodyPart current = new SnakeBodyPart((byte) 15, (byte) 17);
        SnakeBodyPart after = new SnakeBodyPart((byte) 15, (byte) 18);
        // WHEN
        PictureFiles actual = Board.whichSlant(before, current, after);
        // THEN
        Assert.assertEquals(actual, null);
    }

    @Test
    public void testSlamRightUpper() {
        // GIVEN
        SnakeBodyPart before = new SnakeBodyPart((byte) 16, (byte) 16);
        SnakeBodyPart current = new SnakeBodyPart((byte) 15, (byte) 17);
        SnakeBodyPart after = new SnakeBodyPart((byte) 15, (byte) 16);
        // WHEN
        PictureFiles actual = Board.whichSlant(before, current, after);
        // THEN
        Assert.assertEquals(actual, PictureFiles.SLANT_RIGHT_UPPER);
    }

    @Test
    public void testSlamRightDowner() {
        // GIVEN
        SnakeBodyPart before = new SnakeBodyPart((byte) 16, (byte) 16);
        SnakeBodyPart current = new SnakeBodyPart((byte) 15, (byte) 17);
        SnakeBodyPart after = new SnakeBodyPart((byte) 15, (byte) 18);
        // WHEN
        PictureFiles actual = Board.whichSlant(before, current, after);
        // THEN
        Assert.assertEquals(actual, PictureFiles.SLANT_RIGHT_DOWNER);
    }

    @Test
    public void testSlamLeftUpper() {
        // GIVEN
        SnakeBodyPart before = new SnakeBodyPart((byte) 14, (byte) 16);
        SnakeBodyPart current = new SnakeBodyPart((byte) 15, (byte) 17);
        SnakeBodyPart after = new SnakeBodyPart((byte) 15, (byte) 16);
        // WHEN
        PictureFiles actual = Board.whichSlant(before, current, after);
        // THEN
        Assert.assertEquals(actual, PictureFiles.SLANT_LEFT_UPPER);
    }

    @Test
    public void testSlamLeftDowner() {
        // GIVEN
        SnakeBodyPart before = new SnakeBodyPart((byte) 14, (byte) 16);
        SnakeBodyPart current = new SnakeBodyPart((byte) 15, (byte) 17);
        SnakeBodyPart after = new SnakeBodyPart((byte) 15, (byte) 18);
        // WHEN
        PictureFiles actual = Board.whichSlant(before, current, after);
        // THEN
        Assert.assertEquals(actual, PictureFiles.SLANT_LEFT_DOWNER);
    }
}
