package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

public class CircleTest {
    @Test
    @DisplayName("Create circle with min int value")
    public void createCircleMinInt() {
        int coord = Integer.MIN_VALUE;
        Circle circle = new Circle(coord, coord);


        assertEquals(coord-25, circle.getX());
        assertEquals(coord-25, circle.getY());
    }

    @Test
    public void testCreateNewCircle() {
        // Create a new Circle object with the x and y coordinates set to 100.
        Circle circle = new Circle(100, 100);

        // Assert that the Circle object was created successfully.
        assertNotNull(circle);

        // Assert that the Circle object has the correct x and y coordinates.
        assertEquals(100-25, circle.getX());
        assertEquals(100-25, circle.getY());
    }

    @Test
    @DisplayName("Create circle with 0 value")
    public void createCircleZeroCoords() {
        int coord = 0;
        Circle circle = new Circle(coord, coord);


        assertEquals((circle.getX()), coord-25);
        assertEquals((circle.getY()), coord-25);
    }


    @Test
    @DisplayName("Create circle with max int value")
    public void createCircleMaxInt() {
        int coord = Integer.MAX_VALUE;
        Circle circle = new Circle(coord, coord);


        assertEquals(coord-25, circle.getX());
        assertEquals(coord-25, circle.getY());
    }

    @Test
    @DisplayName("Create circle with random coord")
    public void createCircleRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Circle circle = new Circle(randomNumberX, randomNumberY);

        assertEquals(randomNumberX-25, circle.getX());
        assertEquals(randomNumberY-25, circle.getY());
    }


    @Test
    @DisplayName("draw a circle in graphic2D test")
    public void drawTest() {



    }
}
