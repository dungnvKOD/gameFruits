package com.dung.game_fruits.manager;

import com.dung.game_fruits.model.BachGround;
import com.dung.game_fruits.model.Bird;
import com.dung.game_fruits.model.Clound;
import com.dung.game_fruits.model.Item;
import com.dung.game_fruits.view.Gui;
import com.dung.game_fruits.view.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private static final int TURN = 4;
    private static final int NUMBER_CLOUND = 5;
    private static final int SPEED_ITEM=3;

    private Random random;
    private BachGround bachGround;
    private Bird bird;
    private Clound clound;
    private Item item;
    private MyPanel myPanel;

    private int apple;
    private int bom;
    private int watermelon;
    private int banana;
    private int sum;
    private boolean isNextLevel;
    private int speedItem=SPEED_ITEM;
    private int turn = TURN;

    PlayerWav bomb;
    PlayerWav fruit;
    PlayerWav background;
    private ArrayList<Clound> clounds;
    private ArrayList<Item> items;

    public GameManager() {
        bomb=new PlayerWav("Bomb");
        fruit=new PlayerWav("fruit");
        background=new PlayerWav("song");
        background.loop(1000);

        bachGround = new BachGround();
        bird = new Bird(Gui.WIDTH_FRAME / 2, Gui.HIEGTH_FRAME - ImageStore.BIRD_LEFT.getHeight(null) - 30, 10, Bird.LEFT);
        clounds = new ArrayList<>();
        items = new ArrayList<>();
        createClound();
    }

    public void createClound() {
        random = new Random();

        for (int i = 0; i < NUMBER_CLOUND; i++) {
            int level = 1;
            int x = random.nextInt(300) + Gui.WIDTH_FRAME;
            int y = random.nextInt(100);
            int type = random.nextInt(3) + 1;
            int speed = random.nextInt(5) + 2;
            int drop = random.nextInt(Gui.WIDTH_FRAME - 100) + 50;
            Image image = ImageStore.getImage("/res/drawable/cloud" + level + type + ".png");

            clound = new Clound(x, y, speed, level, type, image, drop);
            clounds.add(clound);
        }
    }
    //move
    public void moveBackGround() {
        bachGround.move();
    }

    public void moveBird(int direction) {
        bird.setDirection(direction);
        bird.move();
    }

    public void moveClound() {
        int size = clounds.size();
        for (int i = 0; i < size; i++) {
            clounds.get(i).move();
        }
    }

    public void moveItem(MyPanel myPanel) {
        int size = items.size();
        for (int i = 0; i < size; i++) {
            items.get(i).move();
            if (items.get(i).getY() >= Gui.HIEGTH_FRAME && items.get(i).getType() != Item.TYPE_BOM) {
                items.remove(i);
                turn--;
                if (turn == 0) {
                    int option = JOptionPane.showConfirmDialog(myPanel, "Bạn có muốn chơi tếp ", "EXIT", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        sum = 0;
                        turn=TURN;
                        bachGround.setLevel(1);
                        break;
                    } else {
                        System.exit(0);
                    }
                }
                break;
            }
        }
    }

    //draw
    public void drawBackGround(Graphics2D graphics2D) {
        bachGround.draw(graphics2D);
    }
    public void drawBird(Graphics2D graphics2D) {
        bird.draw(graphics2D);
    }

    public void drawClound(Graphics2D graphics2D) {
        int size = clounds.size();
        for (int i = 0; i < size; i++) {
            clounds.get(i).draw(graphics2D);
            if (clounds.get(i).getX() == clounds.get(i).getDrop()) {
                item = new Item(clounds.get(i).getX(), clounds.get(i).getY(), speedItem, clounds.get(i).getLevel(), clounds.get(i).getType());
                items.add(item);
                drawItem(graphics2D);
            }
        }
    }

    public void drawItem(Graphics2D graphics2D) {
        int size = items.size();
        for (int i = 0; i < size; i++) {
            items.get(i).draw(graphics2D);
        }
    }

    //Colllisistion
    public void MyBirdCollisistionListItem() {
        for (int i = 0; i < items.size(); i++) {
            if (bird.isMyBirdCollisistionListItem(items.get(i))) {
                try {
                    System.out.println("remove");

                    switch (items.get(i).getType()) {
                        case Item.TYPE_BOM:
                            bomb.loop(1);
                            bom++;
                            break;
                        case Item.TYPE_BANANA:
                            fruit.loop(1);
                            banana++;
                            break;
                        case Item.TYPE_WATERMELON:
                            fruit.loop(1);
                            watermelon++;
                            break;
                        case Item.TYPE_APPLE:
                            fruit.loop(1);
                            apple++;
                            break;
                        default:
                            break;
                    }
                    items.remove(i);
                    sum = -bom * 20 + banana * 20 + watermelon * 30 + apple * 40;
                } catch (ArrayIndexOutOfBoundsException o) {
                    System.out.println("tràn mảng");
                }
                break;
            }
        }
    }

    //kiem tra level
    public void kiemTraLevel2() {
        if (getSum() >= 100 && !isNextLevel) {
            items.clear();
            speedItem=11;
            bachGround.setLevel(2);
            System.out.println("setBackground...");
            isNextLevel = true;
        }
    }

    public int getTurn() {
        return turn;
    }
    public int getSum() {
        return sum;
    }
}
