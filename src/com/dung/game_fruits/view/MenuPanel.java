package com.dung.game_fruits.view;

import com.dung.game_fruits.manager.ImageStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends BasePanel implements ActionListener {
    private Button btnGamePlay;
    private OnMenuSelecctListener onMenuSelecctListener;
    private static final String ACTION_PLAY_GAME = "ACTION_PLAY_GAME";
    private JLabel btResult;

    @Override
    public void initContainer() {
        setLayout(null);
    }

    @Override
    public void initComponent() {

        btResult = new JLabel();
        Icon icon = new ImageIcon(getClass().getResource("/res/drawable/btn_playgame.png"));
        btResult.setIcon(icon);
        btResult.setBounds(250, 300, 522, 186);
        add(btResult);
    }

    @Override
    public void registraiListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Sent...");
                onMenuSelecctListener.onMenuSelectedListener("GAME_PLAY_PANEL");
                System.out.println("click...");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btResult = new JLabel();
                Icon icon = new ImageIcon(getClass().getResource("/res/drawable/btn_playgame.png"));
                btResult.setIcon(icon);
                btResult.setBounds(250, 299, 522, 186);

                add(btResult);
            }

            @Override
             public void mouseExited(MouseEvent e) {
                 super.mouseExited(e);
              remove(btResult);
             }
        };
        btResult.addMouseListener(mouseAdapter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_PLAY_GAME:
                System.out.println("Sent...");
                onMenuSelecctListener.onMenuSelectedListener("GAME_PLAY_PANEL");

                break;

            default:
                break;
        }
    }

    public void setOnMenuSelecctListener(OnMenuSelecctListener onMenuSelecctListener) {
        this.onMenuSelecctListener = onMenuSelecctListener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Image image = ImageStore.getImage("/res/drawable/Background" + 1 + ".jpg");//new ImageIcon(getClass().getResource("res/drawable/Background1.jpg")).getImage()
        graphics2D.drawImage(image, 0, 0, null);
        repaint();
    }
}
