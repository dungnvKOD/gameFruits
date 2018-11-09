package com.dung.game_fruits.model;

import java.awt.*;

public abstract class BaseModel {
    int x;
    int y;
    int speed;
    Image image;

    public abstract void draw(Graphics2D graphics2D);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
