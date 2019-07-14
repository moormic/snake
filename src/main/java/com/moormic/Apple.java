package com.moormic;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

import static com.moormic.Board.BOARD_HEIGHT;
import static com.moormic.Board.BOARD_LENGTH;

@Data
class Apple {

    private Coordinate coordinate;

    Coordinate placeApple() {
        // todo: call this method again if apple is placed on the snake
        coordinate = new Coordinate(
                ThreadLocalRandom.current().nextInt(1, BOARD_LENGTH - 1),
                ThreadLocalRandom.current().nextInt(1, BOARD_HEIGHT - 1)
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
