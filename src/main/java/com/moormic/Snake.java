package com.moormic;

import lombok.Getter;
import lombok.Setter;

import static com.moormic.Board.*;

class Snake {

    @Getter
    private Coordinate[] coordinates;
    @Getter
    private int length;

    Snake(int length) {
        coordinates = new Coordinate[BOARD_AREA];
        this.length = length;

        // initialise snake in the middle of the board: todo: seed this randomly
        final int xStart = BOARD_LENGTH / 2;
        final int yStart = BOARD_HEIGHT / 2;
        for (int z = 0; z < length; z++) {
            coordinates[z] = new Coordinate(xStart - (z * DOT_SIZE), yStart);
        }
    }

    void move(Direction direction) {
        // each coordinate replaces the one further forward
        for (int z = length - 1; z > 0; z--) {
            coordinates[z] = coordinates[z-1];
        }
        getHead().move(direction);
    }

    Coordinate getHead() {
        return coordinates[0];
    }

    void grow() {
        length++;
    }
}
