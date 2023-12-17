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
            default -> new Square(x, y);
        };
    }

    public AbstractShape CubeGenerator(String shape, int x, int y, int size){
        return new Cube(x,y,size);
    }

    public AbstractShape GroupShapeGenerator(String shape, int x, int y, int xEnd, int yEnd, List<AbstractShape> shapes){
        return new GroupShape(x, y, xEnd, yEnd, shapes);

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
                    String xString = shapeElement.getElementsByTagName("x").item(0).getTextContent();
                    String yString = shapeElement.getElementsByTagName("y").item(0).getTextContent();
                    int x = Integer.parseInt(xString);
                    int y = Integer.parseInt(yString);

                    if(type.equals("cube")){
                        int size = Integer.parseInt(shapeElement.getElementsByTagName("size").item(0).getTextContent());
                        listShape.add(this.CubeGenerator(type, x+25, y+25, size));
                    } else if(type.equals("groupShape")){
                        String xEndString = shapeElement.getElementsByTagName("x").item(0).getTextContent();
                        String yEndString = shapeElement.getElementsByTagName("y").item(0).getTextContent();   
                        int xEnd = Integer.parseInt(xEndString);
                        int yEnd = Integer.parseInt(yEndString);
                        listShape.add(this.GroupShapeGenerator(type, x+25, y+25, xEnd, yEnd, listShape));
                    }
                    else {
                        listShape.add(this.AbstractShapeGenerator(type, x+25, y+25));
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return listShape;
    }
}
