package com.dung.game_fruits.view;

import javax.swing.*;

public abstract class BasePanel extends JPanel implements Initializer {
    public BasePanel() {
        initComponent();
        initContainer();
        registraiListener();

    }
}
