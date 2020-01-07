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
import java.io.IOException;

class ToolButton extends JRadioButton {
    static int TOOL_SIZE;
    private static ImageIcon[][] icons;
    private final int index;

    static {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(ClassLoader.getSystemResourceAsStream("res/tools.xml"));
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
        }
    }

    ToolButton(int index) {
        this.index = index;
        setIcon(icons[index][0]);
        setSelectedIcon(icons[index][1]);
        addMouseListener(new MouseAdapter() {
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
        //setIcon(new ImageIcon(ClassLoader.getSystemResource(tools[index].get("img").toString())));
        setMargin(new Insets(0, 0, 0, 0));

    }
    public int getIndex() {
        return index;
    }
}
