package com.dung.game_fruits.view;

import com.dung.game_fruits.manager.GameManager;
import com.dung.game_fruits.model.Bird;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.BitSet;

public class MyPanel extends BasePanel {
    private GameManager gameManager;
    private KeyAdapter keyAdapter;
    private BitSet bitSet;
    private JLabel lbDiem;
    private JLabel lbSoDiem;
    private JLabel lbMang;
    private JLabel lbSoMang;
    private boolean isPausegGame = true;


    public MyPanel() {
        super();
        setFocusable(true);
    }

    @Override
    public void initContainer() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    public void initComponent() {
        //TODO
        Font font = new Font("Arial", Font.ITALIC, 30);
        FontMetrics fontMetrics = getFontMetrics(font);

        lbDiem = new JLabel("Điểm : ");//hiển thị  điểm
        lbDiem.setFont(font);
        int widthDiem = fontMetrics.stringWidth(lbDiem.getText());
        int hegdthDiem = fontMetrics.getHeight();
        lbDiem.setBounds(5, 5, widthDiem, hegdthDiem);

        lbSoDiem = new JLabel("0");//hiển thị  điểm
        lbSoDiem.setFont(font);
        int widthSoDiem = fontMetrics.stringWidth(lbSoDiem.getText());
        int hegdthSoDiem = fontMetrics.getHeight();
        lbSoDiem.setBounds(lbDiem.getX() + lbDiem.getWidth() + 5, 5, 150, hegdthSoDiem);

        lbMang = new JLabel("Luot choi: ");//hiển thị  điểm
        lbMang.setFont(font);
        int widthMang = fontMetrics.stringWidth(lbMang.getText());
        int hegdthMang = fontMetrics.getHeight();
        lbMang.setBounds(5, lbDiem.getY() + lbDiem.getHeight() + 5, widthMang, hegdthMang);

        lbSoMang = new JLabel("0");//hiển thị  điểm
        lbSoMang.setFont(font);
        int widthSoMang = fontMetrics.stringWidth(lbSoMang.getText());
        int hegdthSoMang = fontMetrics.getHeight();
        lbSoMang.setBounds(5 + lbMang.getX() + lbMang.getWidth(), lbDiem.getY() + lbDiem.getHeight() + 5, 150, hegdthSoMang);

        //add
        add(lbDiem);
        add(lbSoDiem);
        add(lbMang);
        add(lbSoMang);
    }

    @Override
    public void registraiListener() {
        //TODO
        gameManager = new GameManager();
        bitSet = new BitSet();

        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bitSet.set(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bitSet.set(e.getKeyCode(), false);
            }
        };
        addKeyListener(keyAdapter);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long treamOf = 0;
                while (true) {
                    if (bitSet.get(KeyEvent.VK_LEFT)) {
                        gameManager.moveBird(Bird.LEFT);
                    }
                    if (bitSet.get(KeyEvent.VK_RIGHT)) {
                        gameManager.moveBird(Bird.RIGHT);
                    }
                    if (bitSet.get(KeyEvent.VK_SPACE)) {
                        if (isPausegGame == true && treamOf % 4 == 0) {
                            isPausegGame = false;
                        } else {
                            isPausegGame = true;
                        }
                    }

                    if (isPausegGame) {
                        gameManager.moveBackGround();
                        gameManager.moveClound();
                        gameManager.moveItem(MyPanel.this);
                    }
                    treamOf++;
                    gameManager.MyBirdCollisistionListItem();
                    capNhatDiem();
                    gameManager.kiemTraLevel2();
                    repaint();
                    try {
                        Thread.sleep(9);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        gameManager.drawBackGround(graphics2D);
        gameManager.drawBird(graphics2D);
        gameManager.drawClound(graphics2D);
        gameManager.drawItem(graphics2D);
    }

    private void capNhatDiem() {
        lbSoDiem.setText(gameManager.getSum() + "");
        lbSoMang.setText(gameManager.getTurn() + "");
    }
}
