package com.moormic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SnakeTest {

    private Snake snake;

    @Before
    public void setUp() {
        snake = new Snake(5);
    }

    @Test
    public void test_move() {
        Coordinate[] initialCoords = snake.getCoordinates().clone();
        snake.move(Direction.UP);
        Coordinate[] newCoords = snake.getCoordinates();

        // snake head
        assertThat(newCoords[0], is(initialCoords[0].shift(Direction.UP)));

        // snake body
        for (int z = 1; z < snake.getLength(); z++) {
            assertThat(newCoords[z], is(initialCoords[z-1]));
        }
    }


    @Test
    public void test_grow() {
        snake.grow();
        assertThat(snake.getLength(), is(6));
    }
}