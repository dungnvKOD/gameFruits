package com.dung.game_fruits.model;

import com.dung.game_fruits.manager.ImageStore;

import java.awt.*;

public class BachGround extends BaseModel {
    private int level;

    public BachGround() {
        this.x = 0;
        this.y = 0;
        this.level = 1;
        this.image = ImageStore.getImage("/res/drawable/Background" + level + ".jpg");
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(this.image, x, y, null);
    }

    public void move() {
        this.x --;
        if (this.x <= -2042) {
            this.x = 0;
        }
    }

    public void setLevel(int level) {
        this.level = level;
        this.image = ImageStore.getImage("/res/drawable/Background" + level + ".jpg");
    }
}
