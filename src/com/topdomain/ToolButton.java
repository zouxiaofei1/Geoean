package com.topdomain;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ToolButton extends JRadioButton {
    static {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document document = db.parse(ClassLoader.getSystemResourceAsStream("res/tools.xml"));
            NodeList list = document.getElementsByTagName("tool");
            System.out.println(list.getLength());
            for(int i=0; i<list.getLength(); i++){
                Node tool = list.item(i);
                NamedNodeMap attrs = tool.getAttributes();
                System.out.println(attrs.getNamedItem("id"));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public ToolButton(int index) {
    }
}
