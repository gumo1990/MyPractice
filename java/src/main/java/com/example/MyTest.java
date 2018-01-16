package com.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/**
 * Created by whq on 2018/1/11.
 * @Test--junit测试框架注解
 */

public class MyTest {
   static MyClass test = null;

    @BeforeClass
    public  static void Before() {
        test = new MyClass();
    }

    @AfterClass
    public static void After() throws ParserConfigurationException, IOException, SAXException {
        test = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document parse = documentBuilder.parse("book.xml");;
        parse.getElementsByTagName("oage");

    }
    @Test
    public void test() {

        test.run("wang");
    }



}
