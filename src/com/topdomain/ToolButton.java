package com.topdomain;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class ToolButton extends JRadioButton {
    public static int TOOL_SIZE;
    static URL[] urlDefaults, urlSelects;
    static {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(ClassLoader.getSystemResourceAsStream("res/tools.xml"));
            NodeList list = document.getElementsByTagName("tool");
            //System.out.println(list.getLength());
            TOOL_SIZE = list.getLength();
            for (int i = 0; i < list.getLength(); i++) {
                Node tool = list.item(i);
                NodeList child = tool.getChildNodes();
                for (int j = 0; j < child.getLength(); j++) {
                    if (child.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        //System.out.println(child.item(j).getNodeName() + " " + child.item(j).getTextContent());
                        switch (child.item(j).getNodeName()) {
                            case "img":
                                break;
                            default:
                                System.err.println("file://res/tools.xml\n\t未知的Element:<" + child.item(j).getNodeName()+'>');
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public ToolButton(int index) {
        setIcon(null);
    }
}
