package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Random;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class TriangleTest {
    @Test
    @DisplayName("Create triangle with min int value")
    void createTriangleMinInt() {
        int coord = Integer.MIN_VALUE;
        Triangle triangle = new Triangle(coord, coord);


        assertEquals(coord-25, triangle.x());
        assertEquals(coord-25, triangle.y());
    }

    @Test
    void testCreateNewTriangle() {
        // Create a new Triangle object with the x and y coordinates set to 100.
        Triangle triangle = new Triangle(100, 100);

        // Assert that the Triangle object was created successfully.
        assertNotNull(triangle);

        // Assert that the Triangle object has the correct x and y coordinates.
        assertEquals(100-25, triangle.x());
        assertEquals(100-25, triangle.y());
    }

    @Test
    @DisplayName("Create triangle with 0 value")
    void createTriangleZeroCoords() {
        int coord = 0;
        Triangle triangle = new Triangle(coord, coord);


        assertEquals((triangle.x()), coord-25);
        assertEquals((triangle.y()), coord-25);
    }


    @Test
    @DisplayName("Create triangle with max int value")
    void createTriangleMaxInt() {
        int coord = Integer.MAX_VALUE;
        Triangle triangle = new Triangle(coord, coord);


        assertEquals(coord-25, triangle.x());
        assertEquals(coord-25, triangle.y());
    }

    @Test
    @DisplayName("Create triangle with random coord")
    void createTriangleRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Triangle triangle = new Triangle(randomNumberX, randomNumberY);

        assertEquals(randomNumberX-25, triangle.x());
        assertEquals(randomNumberY-25, triangle.y());
    }

    @Test
    @DisplayName("Test triangle accept visitor")
    void testTriangleAcceptVisitor() {
        Triangle triangle = new Triangle(100, 100);
        XMLVisitor xmlVisitor = new XMLVisitor();
        triangle.accept(xmlVisitor);
        String result = xmlVisitor.getRepresentation();
        String expected = "<shape><type>triangle</type><x>75</x><y>75</y></shape>";
        assertEquals(expected, result);

        JSonVisitor jSonVisitor = new JSonVisitor();
        triangle.accept(jSonVisitor);
        result = jSonVisitor.getRepresentation();
        expected = "{\n\"type\": \"triangle\",\n\"x\": 75,\n\"y\": 75\n}";
        assertEquals(expected, result);
    }
}
