package com.dung.game_fruits.model;

import com.dung.game_fruits.manager.ImageStore;
import com.dung.game_fruits.view.Gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Clound extends BaseModel {

    private int type;
    private int level;
    private int drop;
    private Random random;

    public Clound(int x, int y, int speed, int level, int type, Image image, int drop) {
        this.x = x;
        this.y = y;
        this.drop = drop;
        this.type = type;
        this.speed = speed;
        this.image = image;
        this.level = level;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }

    public void move() {
        random = new Random();
        x -=speed;
        if (x <= 0 - image.getWidth(null)) {
            this.x = random.nextInt(300) + Gui.WIDTH_FRAME;
            this.y = random.nextInt(150);
            this.type = random.nextInt(4) + 1;
            this.speed = random.nextInt(3) + 2;
            this.image = ImageStore.getImage("/res/drawable/cloud" + level + type + ".png");
        }
    }

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getDrop() {
        return drop;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDrop(int drop) {
        this.drop = drop;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
