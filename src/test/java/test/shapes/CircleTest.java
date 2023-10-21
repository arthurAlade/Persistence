package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Random;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

class CircleTest {
    @Test
    @DisplayName("Create circle with min int value")
    void createCircleMinInt() {
        int coord = Integer.MIN_VALUE;
        Circle circle = new Circle(coord, coord);


        assertEquals(coord-25, circle.x());
        assertEquals(coord-25, circle.y());
    }

    @Test
    void testCreateNewCircle() {
        // Create a new Circle object with the x and y coordinates set to 100.
        Circle circle = new Circle(100, 100);

        // Assert that the Circle object was created successfully.
        assertNotNull(circle);

        // Assert that the Circle object has the correct x and y coordinates.
        assertEquals(100-25, circle.x());
        assertEquals(100-25, circle.y());
    }

    @Test
    @DisplayName("Create circle with 0 value")
    void createCircleZeroCoords() {
        int coord = 0;
        Circle circle = new Circle(coord, coord);


        assertEquals((circle.x()), coord-25);
        assertEquals((circle.y()), coord-25);
    }


    @Test
    @DisplayName("Create circle with max int value")
    void createCircleMaxInt() {
        int coord = Integer.MAX_VALUE;
        Circle circle = new Circle(coord, coord);


        assertEquals(coord-25, circle.x());
        assertEquals(coord-25, circle.y());
    }

    @Test
    @DisplayName("Create circle with random coord")
    void createCircleRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Circle circle = new Circle(randomNumberX, randomNumberY);

        assertEquals(randomNumberX-25, circle.x());
        assertEquals(randomNumberY-25, circle.y());
    }

    @Test
    @DisplayName("Test circle accept visitor")
    void testCircleAcceptVisitor() {
        Circle circle = new Circle(100, 100);
        XMLVisitor xmlVisitor = new XMLVisitor();
        circle.accept(xmlVisitor);
        String result = xmlVisitor.getRepresentation();
        String expected = "<shape><type>circle</type><x>75</x><y>75</y></shape>";
        assertEquals(expected, result);

        JSonVisitor jSonVisitor = new JSonVisitor();
        circle.accept(jSonVisitor);
        result = jSonVisitor.getRepresentation();
        expected = "{\n\"type\": \"circle\",\n\"x\": 75,\n\"y\": 75\n}";
        assertEquals(expected, result);
    }
}
