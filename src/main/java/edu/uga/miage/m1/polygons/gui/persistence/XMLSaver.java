package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class XMLSaver extends Saver{

    private final Logger logger = Logger.getLogger(XMLSaver.class.getName());

    public XMLSaver(List<Circle> circleList, List<Square> squareList, List<Triangle> triangleList) {
        super(circleList, squareList, triangleList);
    }

    public void saveShapes(){
        addToSave(new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"));
        addShapes(true);
        addToSave(new StringBuilder("</shapes>"));
    }
    public void saveXML(){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("save.xml"))) {
            writer.write(String.valueOf(getSave()));
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error while saving XML file", e);
        }

    }
}
