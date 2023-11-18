package test.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;

class CubeTest {
    @Test
    @DisplayName("Create cube with min int value")
    void createCubeMinInt() {
        int coord = Integer.MIN_VALUE;
        Cube cube = new Cube(coord, coord);


        assertEquals(coord - 25, cube.getX());
        assertEquals(coord - 25, cube.getY());
    }

    @Test
    void testCreateNewCube() {
        // Create a new Cube object with the x and y coordinates set to 100.
        Cube cube = new Cube(100, 100);

        // Assert that the Cube object was created successfully.
        assertNotNull(cube);

        // Assert that the Cube object has the correct x and y coordinates.
        assertEquals(100 - 25, cube.getX());
        assertEquals(100 - 25, cube.getY());
    }

    @Test
    @DisplayName("Create cube with 0 value")
    void createCubeZeroCoords() {
        int coord = 0;
        Cube cube = new Cube(coord, coord);


        assertEquals((cube.getX()), coord - 25);
        assertEquals((cube.getY()), coord - 25);
    }


    @Test
    @DisplayName("Create cube with max int value")
    void createCubeMaxInt() {
        int coord = Integer.MAX_VALUE;
        Cube cube = new Cube(coord, coord);


        assertEquals(coord - 25, cube.getX());
        assertEquals(coord - 25, cube.getY());
    }

    @Test
    @DisplayName("Create cube with random coord")
    void createCubeRandoomCoord() {
        Random randomX = new Random();
        Random randomY = new Random();

        int randomNumberX = randomX.nextInt();
        int randomNumberY = randomY.nextInt();

        Cube cube = new Cube(randomNumberX, randomNumberY);

        assertEquals(randomNumberX - 25, cube.getX());
        assertEquals(randomNumberY - 25, cube.getY());
    }

    @Test
    @DisplayName("Test cube accept visitor")
    void testCubeAcceptVisitor() {
        Cube cube = new Cube(100, 100);
        XMLVisitor xmlVisitor = new XMLVisitor();
        cube.accept(xmlVisitor);
        String result = xmlVisitor.getRepresentation();
        String expected = "<shape><type>cube</type><x>75</x><y>75</y><size>100</size></shape>";
        assertEquals(expected, result);

        JSonVisitor jSonVisitor = new JSonVisitor();
        cube.accept(jSonVisitor);
        result = jSonVisitor.getRepresentation();
        expected = "{\n\"type\": \"cube\",\n\"x\": 75,\n\"y\": 75,\n\"size\": 100\n}";
        assertEquals(expected, result);
    }

    @Test
    void cubeIsAbstractShape() {
        Cube cube = new Cube(50, 75);
        cube.setX(100);
        cube.setY(100);
        assertTrue(cube instanceof AbstractShape);
    }

    @Test
    void moveCube(){
        Cube cube = new Cube(50, 75);
        cube.setX(100);
        cube.setY(101);
        assertEquals(100, cube.getX());
        assertEquals(101, cube.getY());
    }
}
