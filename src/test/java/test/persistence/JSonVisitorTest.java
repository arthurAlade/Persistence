package test.persistence;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSonVisitorTest {
    Circle circle;
    Square square;
    Triangle triangle;
    JSonVisitor jSonVisitor;
    String result;
    String expected;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30); //x - 25, y -25
        square = new Square(200,25);
        triangle = new Triangle(300,45);
        jSonVisitor = new JSonVisitor();
    }

    @Test
    void getSchema() {
        result = jSonVisitor.getSchema(100, 50, "test");
        expected = "{\n\"type\": \"test\",\n\"x\": 100,\n\"y\": 50\n}";
        assertEquals(expected, result);
    }

    @Test
    void visitCircle() {
        expected = "{\n\"type\": \"circle\",\n\"x\": 100,\n\"y\": 5\n}";
        jSonVisitor.visit(circle);
        result = jSonVisitor.getRepresentation();
        assertEquals(expected, result);
    }

    @Test
    void visitSquare(){
        expected = "{\n\"type\": \"square\",\n\"x\": 175,\n\"y\": 0\n}";
        jSonVisitor.visit(square);
        result = jSonVisitor.getRepresentation();
        assertEquals(expected, result);
    }

    @Test
    void visitTriangle(){
        expected = "{\n\"type\": \"triangle\",\n\"x\": 275,\n\"y\": 20\n}";
        jSonVisitor.visit(triangle);
        result = jSonVisitor.getRepresentation();
        assertEquals(expected, result);
    }


}