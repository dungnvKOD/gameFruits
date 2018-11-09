package com.dung.game_fruits.manager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageStore {
    public static final Image BIRD_LEFT     = ImageStore.getImage("/res/drawable/bird_left1.png");
    public static final Image BIRD_RIGHT    = ImageStore.getImage("/res/drawable/bird_right2.png");
    public static final Image BOM           = ImageStore.getImage("/res/drawable/Fruit14.png");
//    public static final Image APPLE         = ImageStore.getImage("/res/drawable/Fruit12.png");
//    public static final Image BANANA        = ImageStore.getImage("/res/drawable/Fruit11.png");
//    public static final Image WATERMELON    = ImageStore.getImage("/res/drawable/Fruit13.png");


    public static Image getImage(String path) {
        URL url = ImageStore.class.getResource(path);
        Image image = new ImageIcon(url).getImage();
        return image;
    }

}
