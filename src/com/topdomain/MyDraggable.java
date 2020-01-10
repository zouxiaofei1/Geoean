package com.topdomain;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class MyDraggable extends JViewport {
    public JPanel panel;
    MyDraggable(ArrayList<AbstractButton> buttons) {
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        ButtonGroup group = new ButtonGroup();
        for (AbstractButton tmp : buttons) {
            group.add(tmp);
            panel.add(tmp);
        }
        this.setView(panel);
    }

}
