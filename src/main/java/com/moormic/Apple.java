package com.moormic;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

import static com.moormic.Board.*;

@Data
class Apple {

    private Coordinate coordinate;

    void move() {
        coordinate = new Coordinate(
                DOT_SIZE * ThreadLocalRandom.current().nextInt(1, (BOARD_LENGTH - DOT_SIZE) / DOT_SIZE),
                DOT_SIZE * ThreadLocalRandom.current().nextInt(1, (BOARD_HEIGHT - DOT_SIZE) / DOT_SIZE)
        );
    }

    int getX() {
        return coordinate.getX();
    }

    int getY() {
        return coordinate.getY();
    }
}
