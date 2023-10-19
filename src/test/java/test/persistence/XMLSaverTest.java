package test.persistence;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.XMLSaver;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLSaverTest {
    Circle circle;
    XMLSaver xmlSaver;
    List<Visitable> visitableList;
    String result;
    String expected;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30);//x - 25, y -25
        visitableList = new ArrayList<>();
        visitableList.add(circle);
        xmlSaver = new XMLSaver(visitableList);
    }

    @Test
    void saveShapes() {
        expected = """
                <?xml version="1.0" encoding="UTF-8"?>
                <shapes>
                <shape><type>circle</type><x>100</x><y>5</y></shape>
                </shapes>""";
        xmlSaver.saveShapes();
        result = xmlSaver.getSave().toString();
        assertEquals(expected, result);
    }

    @Test
    void saveJSON() {
    //TODO
    }
}

