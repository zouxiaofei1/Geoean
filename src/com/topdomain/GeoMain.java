package com.topdomain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class GeoMain {
    private static final Logger eventLogger = Logger.getLogger("propertyChange");
    final JFrame frame;
    MyDraggable draggable;

    private GeoMain() {
        frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(initContentPane());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GeoMain::new);
    }

    private JSplitPane initContentPane() {

        ArrayList<AbstractButton> toolButtons = new ArrayList<>();

        for (int i = 0; i < ToolButton.TOOL_SIZE; i++) {
            toolButtons.add(new ToolButton(i));
            // toolButtons.get(i).setMnemonic('0' + i);
        }
        JSplitPane ret = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, draggable = new MyDraggable(toolButtons), new PaintPanel());
        ret.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ret.requestFocus();
                System.out.println(ret.hasFocus());

                System.out.println(frame.getMostRecentFocusOwner());
                System.out.println("Parent = " + frame.getMostRecentFocusOwner().getParent());

                if (frame.getMostRecentFocusOwner() instanceof ToolButton) {
                    System.out.println(((ToolButton) frame.getMostRecentFocusOwner()).getIndex());
                }
                Print(frame.getComponents(), 0);
            }

            private void Print(Component[] components, int dep) {
                if (components == null) return;
                for (Component i :
                        components) {
                    System.out.println(dep + ":" + i);
                    if (i instanceof Container) Print(((Container) i).getComponents(), dep + 1);
                }
            }
        });
        ret.setOneTouchExpandable(true);
        ret.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("dividerLocation")) {
                eventLogger.info(evt.getPropertyName() + ":OldValue=" + evt.getOldValue() + " NewValue=" + evt.getNewValue());
                if ((int) evt.getOldValue() > 1 && (int) evt.getNewValue() > (int) evt.getOldValue())
                    ret.setDividerLocation((int) evt.getOldValue());
            }
        });
        ret.setEnabled(false);
        // ret.setRightComponent(new JPanel());
        return ret;
    }

    private static class PaintPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.draw3DRect(0, 0, 10, 10, true);

        }
    }
}
