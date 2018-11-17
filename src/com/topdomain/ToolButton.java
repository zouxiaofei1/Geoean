package com.topdomain;


import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ToolButton extends JRadioButton {
    static {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(ClassLoader.getSystemClassLoader().getResourceAsStream("res/tools.xml"), new MyDefaultHandler());
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

    }

    public ToolButton(int index) {
    }

    private static class MyDefaultHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            System.out.println("MyDefaultHandler.startElement->" + qName);;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length);
            System.out.println("MyDefaultHandler.characters->"+content);
        }
    }
}
