package com.snakegame;
/***
 * Representation of the states of the game
 *
 */
public enum GameState {
    IN_GAME, // No Game over
    GAME_OVER_BY_BITE, // Snake bitten its tail.
    GAME_OVER_BY_WALL // Snake hits the wall.
}
