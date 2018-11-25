package com.topdomain;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ToolButton extends JRadioButton {
    public static int TOOL_SIZE;
    static HashMap[] tools;
    static {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(ClassLoader.getSystemResourceAsStream("res/tools.xml"));
            NodeList list = document.getElementsByTagName("tool");
            //System.out.println(list.getLength());
            TOOL_SIZE = list.getLength();
            tools = new HashMap[TOOL_SIZE];
            for (int i = 0; i < list.getLength(); i++) {
                Node tool = list.item(i);
                NodeList child = tool.getChildNodes();
                tools[i] = new HashMap();
                for(int j=0; j< child.getLength(); j++){
                    if(child.item(j).getNodeType()==Node.ELEMENT_NODE){
                        System.out.println(child.item(j).getNodeName()+" "+child.item(j).getTextContent());
                        tools[i].put(child.item(j).getNodeName(), child.item(j).getTextContent());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public ToolButton(int index) {
        setIcon(new ImageIcon(ClassLoader.getSystemResource(tools[index].get("img").toString())));
    }
}
