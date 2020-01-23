package com.topdomain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

class MyDraggable extends JViewport {
    public static final int SCROLL_UNITS = 2;
    public JPanel panel;
    int oldY = 0;
    boolean needToMove = false;

    MyDraggable(ArrayList<AbstractButton> buttons) {
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        ButtonGroup group = new ButtonGroup();
        for (AbstractButton tmp : buttons) {
            group.add(tmp);
            panel.add(tmp);
            panel.add(Box.createVerticalStrut(10));
        }
        this.setView(panel);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldY = e.getYOnScreen();
                needToMove = false;
                //setViewPosition(new Point());
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                /// if(e.getClickCount());
                int newY = e.getYOnScreen();
                if (needToMove || Math.abs(newY - oldY) > 16) {
                    needToMove = true;
                    setViewPosition(new Point(getViewPosition().x, Math.max(0, getViewPosition().y - newY + oldY)));
                    oldY = newY;
                }
            }
        });
        panel.addMouseWheelListener(wheel -> setViewPosition(new Point(getViewPosition().x, Math.max(0, getViewPosition().y + SCROLL_UNITS * wheel.getUnitsToScroll()))));
    }
}
