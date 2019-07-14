package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.moormic.Board.DOT_SIZE;

@Data
@AllArgsConstructor
class Coordinate {
    private int x;
    private int y;

    void move(Direction direction) {
        switch (direction) {
            case UP: y += DOT_SIZE; break;
            case DOWN: y -= DOT_SIZE; break;
            case RIGHT: x += DOT_SIZE; break;
            case LEFT: x -= DOT_SIZE; break;
        }
    }
}
