package com.dung.game_fruits.model;

import com.dung.game_fruits.manager.ImageStore;

import javax.swing.*;
import java.awt.*;

public class Item extends BaseModel {
    public static final int TYPE_APPLE = 1;
    public static final int TYPE_BANANA = 2;
    public static final int TYPE_WATERMELON = 3;
    public static final int TYPE_BOM = 4;
    private int type;
    private int level;

    public Item(int x, int y, int speed, int level, int type) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        this.level = level;
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        switch (type) {
            case TYPE_APPLE:
                graphics2D.drawImage(ImageStore.getImage("/res/drawable/Fruit" + level + type + ".png"), x, y, null);
                break;
            case TYPE_BOM:
                graphics2D.drawImage(ImageStore.BOM, x, y, null);
                break;
            case TYPE_BANANA:
                graphics2D.drawImage(ImageStore.getImage("/res/drawable/Fruit" + level + type + ".png"), x, y, null);
                break;
            case TYPE_WATERMELON:
                graphics2D.drawImage(ImageStore.getImage("/res/drawable/Fruit" + level + type + ".png"), x, y, null);
                break;
        }
    }

    public void move() {
        y +=speed;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

}
