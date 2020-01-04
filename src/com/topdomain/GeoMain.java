package com.topdomain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class GeoMain {
    private static final Logger eventLogger = Logger.getLogger("propertyChange");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GeoMain::new);
    }

    private GeoMain() {
        final JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(initContentPane());
        frame.setVisible(true);
    }

    private Container initContentPane() {
        JSplitPane ret = new JSplitPane();
        ret.setOneTouchExpandable(true);
        ret.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("dividerLocation")) {
                eventLogger.info(evt.getPropertyName() + ":OldValue=" + evt.getOldValue() + " NewValue=" + evt.getNewValue());
                if ((int) evt.getOldValue() > 1 && (int) evt.getNewValue() > (int) evt.getOldValue())
                    ret.setDividerLocation((int) evt.getOldValue());
            }
        });
        ret.setEnabled(false);
        ArrayList<AbstractButton> buttons = new ArrayList<>();
        for (int i = 0; i < ToolButton.TOOL_SIZE; i++) buttons.add(new ToolButton(i));
        ret.setLeftComponent(new MyDraggable(buttons));
        return ret;
    }
}
