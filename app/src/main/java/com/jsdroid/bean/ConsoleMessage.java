package com.jsdroid.bean;

public class ConsoleMessage {
    //level 0代表info，1代表error
    private int level;
    private String message;

    public ConsoleMessage(int level, String message) {
        this.level = level;
        this.message = message;
    }

    public ConsoleMessage() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
