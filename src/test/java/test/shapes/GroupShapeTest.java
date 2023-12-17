package test.shapes;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Saver;
import edu.uga.miage.m1.polygons.gui.persistence.XMLSaver;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GroupShapeTest {

    Circle circle;
    Triangle triangle;
    GroupShape groupShape;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30);//x - 25, y -25
        triangle = new Triangle(100, 100);
    }


    @Test
    void testCreateGroupShapeMinMaxInt(){
        int coordInit = Integer.MIN_VALUE;
        int coordEnd = Integer.MAX_VALUE;

        groupShape = new GroupShape(coordInit, coordInit);
        groupShape.setxEnd(coordEnd);
        groupShape.setyEnd(coordEnd);
        assertEquals(coordInit -25, groupShape.getX());
        assertEquals(coordInit -25, groupShape.getY());
        assertEquals(coordEnd, groupShape.getxEnd());
        assertEquals(coordEnd, groupShape.getyEnd());
        assertEquals(0, groupShape.getShapes().size());
        assertFalse(groupShape.isGrouped());
    }

    @Test
    void testCreateGroupShapeMaxInt(){
        int coordInit = Integer.MAX_VALUE;
        groupShape = new GroupShape(coordInit, coordInit);

        assertEquals(coordInit -25, groupShape.getX());
        assertEquals(coordInit -25, groupShape.getY());
    }

    @Test
    void testCreateGroupShapeRandom(){
        int randomX = new Random().nextInt();
        int randomY = new Random().nextInt();

        groupShape = new GroupShape(randomX, randomY);

        assertEquals(randomX -25, groupShape.getX());
        assertEquals(randomY -25, groupShape.getY());
    }

    @Test
    void testGroupShapeAddShape(){
        ArrayList<AbstractShape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(triangle);

        groupShape = new GroupShape(0,0);
        groupShape.setShapes(shapes);
        groupShape.setGrouped(true);
        assertEquals(2, groupShape.getShapes().size());
        assertEquals(circle, groupShape.getShapes().get(0));
        assertEquals(triangle, groupShape.getShapes().get(1));
        assertTrue(groupShape.isGrouped());
    }

    @Test
    @Disabled
    void testGroupShapeAcceptVisitor(){
        ArrayList<AbstractShape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(triangle);

        groupShape = new GroupShape(0,0);
        groupShape.setShapes(shapes);
        groupShape.setGrouped(true);

        XMLVisitor xmlVisitor = new XMLVisitor();
        groupShape.accept(xmlVisitor);
        String result = xmlVisitor.getRepresentation();
        String expected = "<shape><type>groupShape</type><x>-25</x><y>-25</y><xEnd>0</xEnd><yEnd>0</yEnd><shape>";
        assertEquals(expected, result);

        JSonVisitor jSonVisitor = new JSonVisitor();
        groupShape.accept(jSonVisitor);
        result = jSonVisitor.getRepresentation();
        expected = "{\n\"type\": \"groupShape\",\n\"x\": -25,\n\"y\": -25,\n\"xEnd\": 0,\n\"yEnd\": 0,\n\"shapes\": [\n";
        assertEquals(expected, result);


    }

}
