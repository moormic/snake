package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.moormic.Board.DOT_SIZE;

@Data
@AllArgsConstructor
class Coordinate {
    private int x;
    private int y;

    Coordinate shift(Direction direction) {
        Coordinate newCoord = new Coordinate(x, y);

        switch (direction) {
            case UP: newCoord = shiftY(-DOT_SIZE); break;
            case DOWN: newCoord = shiftY(DOT_SIZE); break;
            case RIGHT: newCoord = shiftX(DOT_SIZE); break;
            case LEFT: newCoord = shiftX(-DOT_SIZE); break;
        }
        return newCoord;
    }

    private Coordinate shiftX(int dx) {
        return new Coordinate(x + dx, y);
    }

    private Coordinate shiftY(int dy) {
        return new Coordinate(x, y + dy);
    }
}
