package com.moormic;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppleTest {

    private Apple apple;

    @Before
    public void setUp() {
        apple = new Apple();
    }

    @Test
    public void test_move() {
        Coordinate initialCoords = apple.getCoordinate();
        apple.move();
        assertNotEquals(initialCoords, apple.getCoordinate());
    }
}
