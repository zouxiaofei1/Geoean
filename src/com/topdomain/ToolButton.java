package com.topdomain;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Objects;

class ToolButton extends JRadioButton {
    static int TOOL_SIZE;
    private static ImageIcon[][] icons;
    private final int index;

    static {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            System.err.println("Now loading ToolButton static");
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("res/tools.xml")));
            NodeList list = document.getElementsByTagName("tool");
            //System.out.println(list.getLength());
            TOOL_SIZE = list.getLength();
            icons = new ImageIcon[TOOL_SIZE][];
            for (int i = 0; i < list.getLength(); i++) {
                Node tool = list.item(i);
                NodeList child = tool.getChildNodes();
                icons[i] = new ImageIcon[3];
                for (int j = 0; j < child.getLength(); j++) {
                    if (child.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        // System.out.println(child.item(j).getNodeName()+" "+child.item(j).getTextContent());
                        switch (child.item(j).getNodeName()) {
                            case "img":
                                icons[i][0] = new ImageIcon(ClassLoader.getSystemResource(child.item(j).getTextContent()));
                            case "simg":
                                icons[i][1] = new ImageIcon(ClassLoader.getSystemResource(child.item(j).getTextContent()));
                            case "fimg":
                                icons[i][2] = new ImageIcon(ClassLoader.getSystemResource(child.item(j).getTextContent()));
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());;
        }
    }

    ToolButton(int index) {
        this.index = index;
        setIcon(icons[index][0]);
        setSelectedIcon(icons[index][1]);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getModifiersEx());;
                getParent().dispatchEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setIcon(icons[index][2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setIcon(icons[index][0]);
            }
        });
        setMargin(new Insets(0, 2, 0, 2));
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                getParent().dispatchEvent(e);
            }
        });
    }
    public int getIndex() {
        return index;
    }
}
