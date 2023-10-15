package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.logging.Logger;

public class JSONSaver extends Saver{
    private final Logger logger = Logger.getLogger(JSONSaver.class.getName());

    public JSONSaver(List<Circle> circleArrayList, List<Square> squareArrayList, List<Triangle> triangleArrayList) {
        super(circleArrayList, squareArrayList, triangleArrayList);
    }

    public void saveShapes(){
        addToSave(new StringBuilder("{\"shapes\":[\n"));
        addShapes(false);
        addToSave(new StringBuilder("]}"));
    }

    public void saveJSON(){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.json"))) {
            writer.write(String.valueOf(getSave()));
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error while saving JSON file", e);
        }
    }
}
