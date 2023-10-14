package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.logging.Logger;

public class JSONSaver {
    private final StringBuilder save = new StringBuilder();
    private final List<Circle> circleArrayList;
    private final List<Square> squareArrayList;
    private final List<Triangle> triangleArrayList;
    private final Logger logger = Logger.getLogger(JSONSaver.class.getName());

    public JSONSaver(List<Circle> circleArrayList, List<Square> squareArrayList, List<Triangle> triangleArrayList) {
        this.circleArrayList = circleArrayList;
        this.squareArrayList = squareArrayList;
        this.triangleArrayList = triangleArrayList;
    }

    public void addShapes(){
        save.append("{\"shapes\":[\n");
        circleArrayList.forEach(element -> {
            JSonVisitor jSonVisitor = new JSonVisitor();
            jSonVisitor.visit(element);
            save.append(jSonVisitor.getRepresentation());
            save.append(",\n");
        });

        squareArrayList.forEach(element -> {
            JSonVisitor jsonVisitor = new JSonVisitor();
            jsonVisitor.visit(element);
            save.append(jsonVisitor.getRepresentation());
            save.append(",\n");
        });

        triangleArrayList.forEach(element -> {
            JSonVisitor jsonVisitor = new JSonVisitor();
            jsonVisitor.visit(element);
            save.append(jsonVisitor.getRepresentation());
            if (triangleArrayList.indexOf(element) != triangleArrayList.size()-1){
                save.append(",\n");
            }
            else {
                save.append("\n");
            }
        });
        save.append("]}");
    }

    public void saveJSON(){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.json"))) {
            writer.write(String.valueOf(save));
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error while saving JSON file", e);
        }
    }
}
