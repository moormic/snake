package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Coordinate {
    private int x;
    private int y;

    void move(Direction direction) {
        switch (direction) {
            case UP: y++;
            case DOWN: y--;
            case RIGHT: x++;
            case LEFT: x--;
        }
    }
}
