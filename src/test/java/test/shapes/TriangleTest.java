package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

public class TriangleTest {
    @Test
    @DisplayName("Create triangle with min int value")
    public void createTriangleMinInt() {
        int coord = Integer.MIN_VALUE;
        Triangle triangle = new Triangle(coord, coord);


        assertEquals(coord-25, triangle.getX());
        assertEquals(coord-25, triangle.getY());
    }

    @Test
    public void testCreateNewTriangle() {
        // Create a new Triangle object with the x and y coordinates set to 100.
        Triangle triangle = new Triangle(100, 100);

        // Assert that the Triangle object was created successfully.
        assertNotNull(triangle);

        // Assert that the Triangle object has the correct x and y coordinates.
        assertEquals(100-25, triangle.getX());
        assertEquals(100-25, triangle.getY());
    }

    @Test
    @DisplayName("Create triangle with 0 value")
    public void createTriangleZeroCoords() {
        int coord = 0;
        Triangle triangle = new Triangle(coord, coord);


        assertEquals((triangle.getX()), coord-25);
        assertEquals((triangle.getY()), coord-25);
    }


    @Test
    @DisplayName("Create triangle with max int value")
    public void createTriangleMaxInt() {
        int coord = Integer.MAX_VALUE;
        Triangle triangle = new Triangle(coord, coord);


        assertEquals(coord-25, triangle.getX());
        assertEquals(coord-25, triangle.getY());
    }

    @Test
    @DisplayName("Create triangle with random coord")
    public void createTriangleRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Triangle triangle = new Triangle(randomNumberX, randomNumberY);

        assertEquals(randomNumberX-25, triangle.getX());
        assertEquals(randomNumberY-25, triangle.getY());
    }


    @Test
    @DisplayName("draw a triangle in graphic2D test")
    public void drawTest() {



    }
}
