package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Random;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

class SquareTest {
    @Test
    @DisplayName("Create square with min int value")
    void createSquareMinInt() {
        int coord = Integer.MIN_VALUE;
        Square square = new Square(coord, coord);


        assertEquals(coord-25, square.x());
        assertEquals(coord-25, square.y());
    }

    @Test
    void testCreateNewSquare() {
        // Create a new Square object with the x and y coordinates set to 100.
        Square square = new Square(100, 100);

        // Assert that the Square object was created successfully.
        assertNotNull(square);

        // Assert that the Square object has the correct x and y coordinates.
        assertEquals(100-25, square.x());
        assertEquals(100-25, square.y());
    }

    @Test
    @DisplayName("Create square with 0 value")
    void createSquareZeroCoords() {
        int coord = 0;
        Square square = new Square(coord, coord);


        assertEquals((square.x()), coord-25);
        assertEquals((square.y()), coord-25);
    }


    @Test
    @DisplayName("Create square with max int value")
    void createSquareMaxInt() {
        int coord = Integer.MAX_VALUE;
        Square square = new Square(coord, coord);


        assertEquals(coord-25, square.x());
        assertEquals(coord-25, square.y());
    }

    @Test
    @DisplayName("Create square with random coord")
    void createSquareRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Square square = new Square(randomNumberX, randomNumberY);

        assertEquals(randomNumberX-25, square.x());
        assertEquals(randomNumberY-25, square.y());
    }

    @Test
    @DisplayName("Test square accept visitor")
    void testSquareAcceptVisitor() {
        Square square = new Square(100, 100);
        XMLVisitor xmlVisitor = new XMLVisitor();
        square.accept(xmlVisitor);
        String result = xmlVisitor.getRepresentation();
        String expected = "<shape><type>square</type><x>75</x><y>75</y></shape>";
        assertEquals(expected, result);

        JSonVisitor jSonVisitor = new JSonVisitor();
        square.accept(jSonVisitor);
        result = jSonVisitor.getRepresentation();
        expected = "{\n\"type\": \"square\",\n\"x\": 75,\n\"y\": 75\n}";
        assertEquals(expected, result);
    }
}
