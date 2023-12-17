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

public class XMLImporterTest {
    XMLImporter xmlImporter;

    @Test
    public void testAbstractShapeGenerator() {
        XMLImporter importer = new XMLImporter();
        AbstractShape circle = importer.AbstractShapeGenerator("circle", 10, 20);
        assertTrue(circle instanceof Circle);
        assertEquals(10-25, circle.getX());
        assertEquals(20-25, circle.getY());

        AbstractShape triangle = importer.AbstractShapeGenerator("triangle", 30, 40);
        assertTrue(triangle instanceof Triangle);
        assertEquals(30-25, triangle.getX());
        assertEquals(40-25, triangle.getY());

        AbstractShape square = importer.AbstractShapeGenerator("square", 50, 60);
        assertTrue(square instanceof Square);
        assertEquals(50-25, square.getX());
        assertEquals(60-25, square.getY());
    }

    @Test
    public void testCubeGenerator() {
        XMLImporter importer = new XMLImporter();
        AbstractShape cube = importer.CubeGenerator("cube", 10, 20, 30);
        assertTrue(cube instanceof Cube);
        assertEquals(10-25, cube.getX());
        assertEquals(20-25, cube.getY());
        assertEquals(30, ((Cube) cube).getSize());
    }

    @Test
    public void testGroupShapeGenerator() {
        XMLImporter importer = new XMLImporter();
        List<AbstractShape> shapes = List.of(new Circle(10, 20), new Triangle(30, 40));
        AbstractShape groupShape = importer.GroupShapeGenerator("groupShape", 0, 0, 100, 100, shapes);
        assertTrue(groupShape instanceof GroupShape);
        assertEquals(-25, groupShape.getX());
        assertEquals(-25, groupShape.getY());
        assertEquals(100, ((GroupShape) groupShape).getxEnd());
        assertEquals(100, ((GroupShape) groupShape).getyEnd());
        assertEquals(shapes, ((GroupShape) groupShape).getShapes());
    }
}
