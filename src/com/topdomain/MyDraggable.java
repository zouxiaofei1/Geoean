package com.topdomain;

import javax.swing.*;
import java.util.ArrayList;

class MyDraggable extends JViewport {

    MyDraggable(ArrayList<AbstractButton> buttons) {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        for (AbstractButton tmp : buttons) {
            ButtonGroup group = new ButtonGroup();
            group.add(tmp);
            panel.add(tmp);
        }
        this.setView(panel);
    }

}
