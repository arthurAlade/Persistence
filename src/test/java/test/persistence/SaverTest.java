package test.persistence;

import edu.uga.miage.m1.polygons.gui.persistence.Saver;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaverTest {
    Circle circle;
    Circle circle2;
    Square square;
    Triangle triangle;
    String result;
    String expected;
    Saver saver;
    List<Visitable> visitablesList;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30); //x - 25, y -25
        circle2 = new Circle(150,50); //x - 25, y -25
        square = new Square(200,25);
        triangle = new Triangle(300,45);

        visitablesList = new ArrayList<>();
        visitablesList.add(circle);
        visitablesList.add(square);
        visitablesList.add(circle2);
        visitablesList.add(triangle);

        saver = new Saver(visitablesList);
    }

    @Test
    void addShapesTest(){
        expected = """
                {
                "type": "circle",
                "x": 100,
                "y": 5
                },
                {
                "type": "square",
                "x": 175,
                "y": 0
                },
                {
                "type": "circle",
                "x": 125,
                "y": 25
                },
                {
                "type": "triangle",
                "x": 275,
                "y": 20
                }
                """;
        saver.addShapes(false);
        result = saver.getSave().toString();
        assertEquals(expected, result);
    }


}
