package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class XMLSaver {
    private StringBuilder save = new StringBuilder("");
    private ArrayList<Circle> m_shapes_circles ;
    private ArrayList<Square> m_shapes_squares ;
    private ArrayList<Triangle> m_shapes_triangles ;

    public XMLSaver(ArrayList<Circle> m_shapes_circles, ArrayList<Square> m_shapes_squares, ArrayList<Triangle> m_shapes_triangles) {
        this.m_shapes_circles = m_shapes_circles;
        this.m_shapes_squares = m_shapes_squares;
        this.m_shapes_triangles = m_shapes_triangles;
    }

    public void addShapes(){
        save.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        save.append("<shapes>\n");
        m_shapes_circles.forEach(element -> {
            XMLVisitor xmlVisitor = new XMLVisitor();
            xmlVisitor.visit(element);
            save.append(xmlVisitor.getRepresentation());
            save.append("\n");
        });

        m_shapes_squares.forEach(element -> {
            XMLVisitor xmlVisitor = new XMLVisitor();
            xmlVisitor.visit(element);
            save.append(xmlVisitor.getRepresentation());
            save.append("\n");
        });

        m_shapes_triangles.forEach(element -> {
            XMLVisitor xmlVisitor = new XMLVisitor();
            xmlVisitor.visit(element);
            save.append(xmlVisitor.getRepresentation());
            save.append("\n");
        });
        save.append("</shapes>");
    }
    public void saveXML(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("save.xml"));
            System.out.println(save);

            writer.write(String.valueOf(save));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
