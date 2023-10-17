package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

public class SquareTest {
    @Test
    @DisplayName("Create square with min int value")
    public void createSquareMinInt() {
        int coord = Integer.MIN_VALUE;
        Square square = new Square(coord, coord);


        assertEquals(coord-25, square.getX());
        assertEquals(coord-25, square.getY());
    }

    @Test
    public void testCreateNewSquare() {
        // Create a new Square object with the x and y coordinates set to 100.
        Square square = new Square(100, 100);

        // Assert that the Square object was created successfully.
        assertNotNull(square);

        // Assert that the Square object has the correct x and y coordinates.
        assertEquals(100-25, square.getX());
        assertEquals(100-25, square.getY());
    }

    @Test
    @DisplayName("Create square with 0 value")
    public void createSquareZeroCoords() {
        int coord = 0;
        Square square = new Square(coord, coord);


        assertEquals((square.getX()), coord-25);
        assertEquals((square.getY()), coord-25);
    }


    @Test
    @DisplayName("Create square with max int value")
    public void createSquareMaxInt() {
        int coord = Integer.MAX_VALUE;
        Square square = new Square(coord, coord);


        assertEquals(coord-25, square.getX());
        assertEquals(coord-25, square.getY());
    }

    @Test
    @DisplayName("Create square with random coord")
    public void createSquareRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Square square = new Square(randomNumberX, randomNumberY);

        assertEquals(randomNumberX-25, square.getX());
        assertEquals(randomNumberY-25, square.getY());
    }

    @Test
    @DisplayName("draw a square in graphic2D test")
    public void drawTest() {



    }
}
