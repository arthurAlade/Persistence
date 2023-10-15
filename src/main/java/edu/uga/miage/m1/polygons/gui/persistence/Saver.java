package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

import java.util.List;

public class Saver {
    private final StringBuilder save = new StringBuilder();
    private final List<Circle> circleList;
    private final List<Square> squareList;
    private final List<Triangle> triangleList;
    private final int totalListSize;
    private int index = 0;

    public Saver(List<Circle> circleList, List<Square> squareList, List<Triangle> triangleList) {
        this.circleList = circleList;
        this.squareList = squareList;
        this.triangleList = triangleList;
        this.totalListSize = circleList.size() + squareList.size() + triangleList.size();
    }

    public void addShapes(boolean xml){
        Visitor visitor = xml ? new XMLVisitor() : new JSonVisitor();
        circleList.forEach(element -> {
            visitor.visit(element);
            save.append(visitor.getRepresentation());
            save.append(xml ? "\n" : getEndShape());
            index++;
        });

        squareList.forEach(element -> {
            visitor.visit(element);
            save.append(visitor.getRepresentation());
            save.append(xml ? "\n" : getEndShape());
            index++;
        });

        triangleList.forEach(element -> {
            visitor.visit(element);
            save.append(visitor.getRepresentation());
            save.append(xml ? "\n" : getEndShape());
            index++;
        });
    }


    private String getEndShape(){
        return index == totalListSize - 1 ? "\n" : ",\n";
    }

    public StringBuilder getSave() {
        return save;
    }

    public void addToSave(StringBuilder save) {
        this.save.append(save);
    }
}
