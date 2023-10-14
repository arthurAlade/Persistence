package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONSaver {
    private StringBuilder save = new StringBuilder("");
    private ArrayList<Circle> m_shapes_circles ;
    private ArrayList<Square> m_shapes_squares ;
    private ArrayList<Triangle> m_shapes_triangles ;

    public JSONSaver(ArrayList<Circle> m_shapes_circles, ArrayList<Square> m_shapes_squares, ArrayList<Triangle> m_shapes_triangles) {
        this.m_shapes_circles = m_shapes_circles;
        this.m_shapes_squares = m_shapes_squares;
        this.m_shapes_triangles = m_shapes_triangles;
    }

    public void addShapes(){
        save.append("{\"shapes\":[\n");
        m_shapes_circles.forEach(element -> {
            JSonVisitor jSonVisitor = new JSonVisitor();
            jSonVisitor.visit(element);
            save.append(jSonVisitor.getRepresentation());
            save.append(",\n");
        });

        m_shapes_squares.forEach(element -> {
            JSonVisitor jsonVisitor = new JSonVisitor();
            jsonVisitor.visit(element);
            save.append(jsonVisitor.getRepresentation());
            save.append(",\n");
        });

        m_shapes_triangles.forEach(element -> {
            JSonVisitor jsonVisitor = new JSonVisitor();
            jsonVisitor.visit(element);
            save.append(jsonVisitor.getRepresentation());
            if (m_shapes_triangles.indexOf(element) != m_shapes_triangles.size()-1){
                save.append(",\n");
            }
            else {
                save.append("\n");
            }
        });
        save.append("]}");
    }

    public void saveJSON(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("save.json"));
            writer.write(String.valueOf(save));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
