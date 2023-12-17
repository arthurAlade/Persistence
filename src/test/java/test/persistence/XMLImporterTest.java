package test.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.persistence.XMLImporter;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class XMLImporterTest {
    XMLImporter xmlImporter;

    @Test
    void testAbstractShapeGenerator() {
        XMLImporter importer = new XMLImporter();
        AbstractShape circle = importer.abstractShapeGenerator("circle", 10, 20);
        assertTrue(circle instanceof Circle);
        assertEquals(10-25, circle.getX());
        assertEquals(20-25, circle.getY());

        AbstractShape triangle = importer.abstractShapeGenerator("triangle", 30, 40);
        assertTrue(triangle instanceof Triangle);
        assertEquals(30-25, triangle.getX());
        assertEquals(40-25, triangle.getY());

        AbstractShape square = importer.abstractShapeGenerator("square", 50, 60);
        assertTrue(square instanceof Square);
        assertEquals(50-25, square.getX());
        assertEquals(60-25, square.getY());
    }

    @Test
    void testCubeGenerator() {
        XMLImporter importer = new XMLImporter();
        AbstractShape cube = importer.cubeGenerator(10, 20, 30);
        assertTrue(cube instanceof Cube);
        assertEquals(10-25, cube.getX());
        assertEquals(20-25, cube.getY());
        assertEquals(30, ((Cube) cube).getSize());
    }

    @Test
    void testGroupShapeGenerator() {
        XMLImporter importer = new XMLImporter();
        List<AbstractShape> shapes = List.of(new Circle(10, 20), new Triangle(30, 40));
        AbstractShape groupShape = importer.groupShapeGenerator( 0, 0, 100, 100, shapes);
        assertTrue(groupShape instanceof GroupShape);
        assertEquals(-25, groupShape.getX());
        assertEquals(-25, groupShape.getY());
        assertEquals(100, ((GroupShape) groupShape).getxEnd());
        assertEquals(100, ((GroupShape) groupShape).getyEnd());
        assertEquals(shapes, ((GroupShape) groupShape).getShapes());
    }
}
