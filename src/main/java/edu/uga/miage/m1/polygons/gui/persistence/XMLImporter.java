package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLImporter {
    public XMLImporter() {
    }

    public AbstractShape AbstractShapeGenerator(String shapeType, int x, int y){

        return switch (shapeType) {
            case "circle" -> new Circle(x, y);
            case "triangle" -> new Triangle(x, y);
            case "cube" -> new Cube(x, y);
            default -> new Square(x, y);
        };
    }

    public List<AbstractShape> importAbstractShape(String filename){
        List<AbstractShape> listShape = new ArrayList<AbstractShape>();

        File inputFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("shape");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node shapeNode = nList.item(temp);

                if (shapeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element shapeElement = (Element) shapeNode;

                    // Get parse and extract type, x and y from XML file
                    String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
                    int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent()+25);
                    int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent()+25);

                    listShape.add(this.AbstractShapeGenerator(type, x, y));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


        return listShape;

    }
}
