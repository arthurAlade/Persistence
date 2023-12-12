package edu.uga.miage.m1.polygons.gui.persistence;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLImporter {
    public XMLImporter() {
    }

    public AbstractShape AbstractShapeGenerator(String shapeType, int x, int y){
        AbstractShape shape = null;

        switch(shapeType){
            case "circle": shape = new Circle(x, y); break;
            case "triangle": shape = new Triangle(x, y); break;
            case "cube": shape = new Cube(x, y); break;
            default: shape = new Square(x, y); break;
        }

        return shape;
    }
    // this method also draw the shapes
    public boolean importXML(String filename, Graphics2D g2){
        boolean status = true;
        List<AbstractShape> listShape = importAbstractShape("save.xml");

        if(listShape == null) status = false;

        for(AbstractShape shape : listShape){
            shape.draw(g2);
        }

        return status;
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


        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }


        return listShape;

    }


    public boolean importXML() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("save.xml"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error while reading XML file");
            return false;
        }
        return true;
    }
}
