package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class XMLSaver extends Saver{

    private final Logger logger = Logger.getLogger(XMLSaver.class.getName());

    public XMLSaver(List<AbstractShape> visitablesList) {
        super(visitablesList);
    }
    public void saveShapes(){
        addToSave(new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"));
        addShapes(true);
        addToSave(new StringBuilder("</shapes>"));
    }
    public boolean saveXML(){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("save.xml"))) {
            writer.write(String.valueOf(getSave()));
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error while saving XML file", e);
            return false;
        }
        return true;
    }
}
