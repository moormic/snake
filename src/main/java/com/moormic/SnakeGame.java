package com.moormic;

public class SnakeGame {
    public String getGreeting() {
        return "Hello from Snake Game!";
    }

    public static void main(String[] args) {
        System.out.println(new SnakeGame().getGreeting());
    }
}
