package test.persistence;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLVisitorTest {
    Circle circle;
    Square square;
    Triangle triangle;
    XMLVisitor xmlVisitor;
    String result;
    String expected;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30); //x - 25, y -25
        square = new Square(200,25);
        triangle = new Triangle(300,45);
        xmlVisitor = new XMLVisitor();
    }

    @Test
    @DisplayName("Test the XML schema")
    void getSchema() {
        result = xmlVisitor.getSchema(100, 50, "test");
        expected = "<shape><type>test</type><x>100</x><y>50</y></shape>";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test XMLVisitor visit(circle)")
    void visitCircle() {
        expected = "<shape><type>circle</type><x>100</x><y>5</y></shape>";
        xmlVisitor.visit(circle);
        result = xmlVisitor.getRepresentation();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test XMLVisitor visit(square)")
    void visitSquare(){
        expected = "<shape><type>square</type><x>175</x><y>0</y></shape>";
        xmlVisitor.visit(square);
        result = xmlVisitor.getRepresentation();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test XMLVisitor visit(triangle)")
    void visitTriangle(){
        expected = "<shape><type>triangle</type><x>275</x><y>20</y></shape>";
        xmlVisitor.visit(triangle);
        result = xmlVisitor.getRepresentation();
        assertEquals(expected, result);
    }


}