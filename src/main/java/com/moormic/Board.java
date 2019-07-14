package com.moormic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class Board extends JPanel implements ActionListener {

    private static final int DELAY = 140;       // speed of the game
    static final int BOARD_HEIGHT = 300;
    static final int BOARD_LENGTH = 300;
    static final int DOT_SIZE = 10;
    static final int BOARD_AREA = (BOARD_HEIGHT * BOARD_LENGTH) / (DOT_SIZE * DOT_SIZE);    // max number of dots on board (BOARD_HEIGHT * BOARD_LENGTH)

    private int score = 0;
    private Snake snake;
    private Apple apple;
    private Direction direction = Direction.RIGHT;
    private Timer timer;
    private Image snakeHeadImage;
    private Image snakeBodyImage;
    private Image appleImage;

    private volatile boolean gameRunning = false;

    Board() {
        snake = new Snake(5);
        apple = new Apple();
        timer = new Timer(DELAY, this);
        snakeHeadImage = new ImageIcon("src/main/resources/head.png").getImage();
        snakeBodyImage = new ImageIcon("src/main/resources/dot.png").getImage();
        appleImage = new ImageIcon("src/main/resources/apple.png").getImage();

        initBoard();
        startGame();
    }

    private void initBoard() {
        addKeyListener(new RightAngleKeyAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_LENGTH, BOARD_HEIGHT));
    }

    private void startGame() {
        gameRunning = true;
        apple.move();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImages(g);
    }

    private void drawImages(Graphics g) {
        if (gameRunning) {
            // apple
            g.drawImage(appleImage, apple.getX(), apple.getY(),this);
            // head
            g.drawImage(snakeHeadImage, snake.getHead().getX(), snake.getHead().getY(), this);
            // body
            Coordinate[] coords = snake.getCoordinates();
            for (int z = 1; z < snake.getLength(); z++) {
                g.drawImage(snakeBodyImage, coords[z].getX(), coords[z].getY(), this);
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            checkAppleEaten();
            checkCollision();
            snake.move(direction);
        }
        repaint();
    }

    private void checkAppleEaten() {
        if (apple.getCoordinate().equals(snake.getHead())) {
            snake.grow();
            score++;
            moveApple();
        }
    }

    private void moveApple() {
        // if the apple has moved onto the snake body, move it again
        boolean appleOnSnake = false;
        do {
            apple.move();
            for (int z = 0; z < snake.getLength(); z++) {
                if (apple.getCoordinate().equals(snake.getCoordinates()[z])) {
                    appleOnSnake = true;
                    break;
                }
            }
            apple.move();
        } while (appleOnSnake);
    }

    private void checkCollision() {
        Coordinate snakeHeadCoords = snake.getHead();
        Coordinate[] snakeBodyCoords = snake.getCoordinates();

        // snake colliding with itself (can only do so if length > 4)
        for (int z = snake.getLength(); z > 4; z--) {
            if (snakeHeadCoords.equals(snakeBodyCoords[z])) {
               gameRunning = false;
            }
        }

        // snake colliding with walls
        if (snakeHeadCoords.getX() <= 0 || snakeHeadCoords.getX() >= BOARD_LENGTH ||
                snakeHeadCoords.getY() <= 0 || snakeHeadCoords.getY() >= BOARD_HEIGHT)
        {
            gameRunning = false;
        }

        if (!gameRunning) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (BOARD_LENGTH - metr.stringWidth(msg)) / 2, (BOARD_HEIGHT - 25) / 2);
        g.drawString(String.format("Score: %d", score), (BOARD_LENGTH - metr.stringWidth(msg)) / 2, (BOARD_HEIGHT + 25) / 2);
    }

    private class RightAngleKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            // snake can only turn at right-angles, can't turn back on itself
            if (key == VK_UP && !direction.equals(Direction.DOWN)) {
                direction = Direction.UP;
            }
            if (key == VK_DOWN && !direction.equals(Direction.UP)) {
                direction = Direction.DOWN;
            }
            if (key == VK_LEFT && !direction.equals(Direction.RIGHT)) {
                direction = Direction.LEFT;
            }
            if (key == VK_RIGHT && !direction.equals(Direction.LEFT)) {
                direction = Direction.RIGHT;
            }
        }
    }
}
