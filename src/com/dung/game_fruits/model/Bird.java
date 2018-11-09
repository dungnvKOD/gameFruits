package com.dung.game_fruits.model;

import com.dung.game_fruits.manager.ImageStore;
import com.dung.game_fruits.view.Gui;

import java.awt.*;

public class Bird extends BaseModel {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private int direction;

    public Bird(int x, int y, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (direction) {
            case LEFT:
                graphics2D.drawImage(ImageStore.BIRD_LEFT, x, y, null);
                break;
            case RIGHT:
                graphics2D.drawImage(ImageStore.BIRD_RIGHT, x, y, null);
        }
    }

    public void move() {
        switch (direction) {
            case LEFT:
                x -= speed;
                if (x <= 0) {
                    x = 0;
                }
                break;
            case RIGHT:
                x += speed;
                if (x >= Gui.WIDTH_FRAME - ImageStore.BIRD_LEFT.getWidth(null))
                    x = Gui.WIDTH_FRAME - ImageStore.BIRD_LEFT.getWidth(null);
                break;
            default:
                break;
        }
    }

    public boolean isMyBirdCollisistionListItem(Item item) {
        Rectangle rectangleItem = new Rectangle(item.x, item.y, ImageStore.BOM.getWidth(null)-10, ImageStore.BOM.getHeight(null)-10);
        Rectangle rectangleBird = new Rectangle(this.x, this.y, ImageStore.BIRD_LEFT.getWidth(null)-10, ImageStore.BIRD_RIGHT.getHeight(null)-10);

        if (rectangleBird.intersects(rectangleItem)) {
            return true;
        }
        return false;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


}
