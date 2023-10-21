package test.persistence;

import edu.uga.miage.m1.polygons.gui.persistence.JSONSaver;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONSaverTest {
    Circle circle;
    JSONSaver jsonSaver;
    List<Visitable> visitableList;
    String result;
    String expected;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30);//x - 25, y -25
        visitableList = new ArrayList<>();
        visitableList.add(circle);
        jsonSaver = new JSONSaver(visitableList);
    }

    @Test
    void saveShapes() {
        expected = """
                {"shapes":[
                {
                "type": "circle",
                "x": 100,
                "y": 5
                }
                ]}""";
        jsonSaver.saveShapes();
        result = jsonSaver.getSave().toString();
        assertEquals(expected, result);
    }

}