package com.topdomain;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MyDraggable extends JViewport {
    JPanel panel = new JPanel();
    ButtonGroup group = new ButtonGroup();

    MyDraggable(ArrayList<JButton> buttons) {
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        for (int i = 0; i < buttons.size(); i++) {
            JButton tmp = buttons.get(i);
            URL url = getClass().getClassLoader().getResource("images/hello.png");
            Image img = Toolkit.getDefaultToolkit().createImage(url).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            tmp.setIcon(new ImageIcon(img));
            tmp.setPreferredSize(new Dimension(40, 40));
            tmp.setFocusPainted(false);
            group.add(tmp);
            panel.add(tmp);
        }
        this.setView(panel);
    }

}
