package com.moormic;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {

    private SnakeGame() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame game = new SnakeGame();
            game.setVisible(true);
        });
    }

}
