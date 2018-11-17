package com.topdomain;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MyDraggable extends JViewport {
    JPanel panel = new JPanel();
    ButtonGroup group = new ButtonGroup();

    MyDraggable(ArrayList<AbstractButton> buttons) {
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        for (int i = 0; i < buttons.size(); i++) {
            AbstractButton tmp = buttons.get(i);
            group.add(tmp);
            panel.add(tmp);
        }
        this.setView(panel);
    }

}
