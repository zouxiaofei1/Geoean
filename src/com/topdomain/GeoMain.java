package com.topdomain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class GeoMain {
    private static final Logger eventLogger = Logger.getLogger("propertyChange");


    private GeoMain() {
        final JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(initContentPane());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GeoMain::new);
    }

    private Container initContentPane() {

        ArrayList<AbstractButton> toolButtons = new ArrayList<>();

        for (int i = 0; i < ToolButton.TOOL_SIZE; i++) {
            toolButtons.add(new ToolButton(i));
        }
        JSplitPane ret = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new MyDraggable(toolButtons), new Panel());
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
}
