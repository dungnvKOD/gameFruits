package com.dung.game_fruits.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui extends JFrame implements Initializer, OnMenuSelecctListener {

    public static final int WIDTH_FRAME = 1021;
    public static final int HIEGTH_FRAME = 768;

    private MenuPanel menuPanel;
    private MyPanel myPanel;
    private WindowAdapter windowAdapter;

    public Gui() {
        initComponent();
        initContainer();
        registraiListener();
    }

    @Override
    public void initContainer() {
        setLayout(new CardLayout());
        setTitle("game Fruits");
        setSize(WIDTH_FRAME, HIEGTH_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void initComponent() {
        menuPanel = new MenuPanel();
        getContentPane().add(menuPanel);
    }

    @Override
    public void registraiListener() {

        menuPanel.setOnMenuSelecctListener(this);
        windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(Gui.this, "Bạn có muốn đóng cửa sổ ?", "EXIT", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        };
        addWindowListener(windowAdapter);
    }

    @Override
    public void onMenuSelectedListener(String name) {
        switch (name) {
            case "GAME_PLAY_PANEL":
                System.out.println("Receive...");
                getContentPane().remove(menuPanel);
                myPanel = new MyPanel();
                getContentPane().add(myPanel);
                myPanel.requestFocusInWindow();
                getContentPane().validate();
                break;
            default:
                break;
        }
    }
}
