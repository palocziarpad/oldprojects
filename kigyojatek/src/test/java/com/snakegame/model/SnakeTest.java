package com.snakegame.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snakegame.BodyPartType;
import com.snakegame.Direction;

public class SnakeTest {
    @Test
    public void testSnakeSize() {
        // GIVEN
        int expected = 3;
        Snake snake = new Snake();
        snake.eat(new SnakeBodyPart((byte) 12, (byte) 11, BodyPartType.HEAD));
        snake.eat(new SnakeBodyPart((byte) 13, (byte) 11, BodyPartType.TORSO));
        snake.eat(new SnakeBodyPart((byte) 14, (byte) 11, BodyPartType.TAIL));
        // WHEN
        int actual = snake.getSize();
        // THEN
        Assert.assertEquals(actual, expected, "The size of the snake is different from the expected " + expected);
    }

    @Test
    public void testSnakeHead() {
        // GIVEN
        SnakeBodyPart expected = new SnakeBodyPart((byte) 13, (byte) 11, BodyPartType.HEAD);
        Snake snake = new Snake();
        snake.eat(expected);
        snake.eat(new SnakeBodyPart((byte) 14, (byte) 11, BodyPartType.TORSO));
        snake.eat(new SnakeBodyPart((byte) 15, (byte) 11, BodyPartType.TAIL));

        // WHEN
        SnakeBodyPart actual = snake.getHead();
        // THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSnakeTail() {
        // GIVEN
        SnakeBodyPart expected = new SnakeBodyPart((byte) 12, (byte) 11, BodyPartType.TAIL);
        Snake snake = new Snake();
        snake.eat(new SnakeBodyPart((byte) 13, (byte) 11, BodyPartType.HEAD));
        snake.eat(new SnakeBodyPart((byte) 14, (byte) 11, BodyPartType.TORSO));
        snake.eat(expected);

        // WHEN
        SnakeBodyPart actual = snake.getTail();
        // THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testHeadNotInBody() {
        // GIVEN
        boolean expected = false;
        Snake snake = new Snake();
        snake.eat(new SnakeBodyPart((byte) 13, (byte) 11, BodyPartType.HEAD));
        snake.eat(new SnakeBodyPart((byte) 14, (byte) 11, BodyPartType.TORSO));
        snake.eat(new SnakeBodyPart((byte) 15, (byte) 11, BodyPartType.TAIL));

        // WHEN
        boolean actual = snake.isHeadInBody();
        // THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testHeadIsInBody() {
        // GIVEN
        boolean expected = true;
        Snake snake = new Snake();
        snake.eat(new SnakeBodyPart((byte) 15, (byte) 11, BodyPartType.HEAD));
        snake.eat(new SnakeBodyPart((byte) 14, (byte) 11, BodyPartType.TORSO));
        snake.eat(new SnakeBodyPart((byte) 15, (byte) 11, BodyPartType.TAIL));

        // WHEN
        boolean actual = snake.isHeadInBody();
        // THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDoStep() {
        // GIVEN
        Snake snake = new Snake();
        SnakeBodyPart head = new SnakeBodyPart((byte) 15, (byte) 11, BodyPartType.HEAD);
        SnakeBodyPart tail = new SnakeBodyPart((byte) 13, (byte) 11, BodyPartType.TAIL);
        snake.eat(head);
        snake.eat(new SnakeBodyPart((byte) 14, (byte) 11, BodyPartType.TORSO));
        snake.eat(tail);
        // WHEN
        snake.doStep(Direction.UP);
        // THEN
        Assert.assertTrue(snake.getHead().getYCoordinate() < head.getYCoordinate());
        Assert.assertTrue(snake.getHead().getXCoordinate() == head.getXCoordinate());
        Assert.assertTrue(snake.getTail().getYCoordinate() != tail.getYCoordinate());
        Assert.assertTrue(snake.getTail().getXCoordinate() != tail.getXCoordinate());

    }
}
