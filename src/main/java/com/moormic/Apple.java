package com.moormic;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
class Apple {

    private Coordinate coordinate;

    Coordinate placeApple(int xMax, int yMax) {
        // todo: call this method again if apple is placed on the snake
        coordinate = new Coordinate(
                ThreadLocalRandom.current().nextInt(1, xMax - 1),
                ThreadLocalRandom.current().nextInt(1, yMax - 1)
        );
        return coordinate;
    }

    int getX() {
        return coordinate.getX();
    }

    int getY() {
        return coordinate.getY();
    }
}
