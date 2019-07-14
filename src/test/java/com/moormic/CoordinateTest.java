package com.moormic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CoordinateTest {

    private Coordinate coordinate;

    @Before
    public void setUp() {
        coordinate = new Coordinate(10, 10);
    }

    @Test
    public void test_shift() {
        assertThat(coordinate.shift(Direction.UP), is(new Coordinate(10, 0)));
        assertThat(coordinate.shift(Direction.DOWN), is(new Coordinate(10, 20)));
        assertThat(coordinate.shift(Direction.LEFT), is(new Coordinate(0, 10)));
        assertThat(coordinate.shift(Direction.RIGHT), is(new Coordinate(20, 10)));
    }

}