package test.persistence;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.XMLSaver;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLSaverTest {
    Circle circle;
    Square square;
    Triangle triangle;
    GroupShape groupShape;
    XMLSaver xmlSaver;
    List<AbstractShape> abstractShapes;
    String result;
    String expected;

    @BeforeEach
    void setUp() {
        circle = new Circle(125,30);//x - 25, y -25
        square = new Square(100, 75);
        triangle = new Triangle(100, 35);
        groupShape = new GroupShape(100, 25);
        groupShape.setxEnd(200);
        groupShape.setyEnd(100);

        abstractShapes = new ArrayList<>();
    }

    @Test
    void saveOneShape() {
        abstractShapes.add(circle);
        xmlSaver = new XMLSaver(abstractShapes);
        expected = """
                <?xml version="1.0" encoding="UTF-8"?>
                <shapes>
                \t<shape><type>circle</type><x>100</x><y>5</y></shape>
                </shapes>""";
        xmlSaver.saveShapes();
        result = xmlSaver.getSave().toString();
        assertEquals(expected, result);
    }

    @Test
    void saveOneGroupShape(){
        groupShape.setShapes(List.of(square, triangle));
        abstractShapes.add(groupShape);
        abstractShapes.add(circle);
        xmlSaver = new XMLSaver(abstractShapes);
        expected = """
                <?xml version="1.0" encoding="UTF-8"?>
                <shapes>
                \t<shape><type>groupShape</type><x>75</x><y>0</y><xEnd>200</xEnd><yEnd>100</yEnd>
                \t\t<shapes>
                \t\t\t<shape><type>square</type><x>75</x><y>50</y></shape>
                \t\t\t<shape><type>triangle</type><x>75</x><y>10</y></shape>
                \t\t</shapes>
                \t</shape>
                \t<shape><type>circle</type><x>100</x><y>5</y></shape>
                </shapes>""";
        xmlSaver.saveShapes();
        result = xmlSaver.getSave().toString();
        assertEquals(expected, result);
    }

    @Test
    void saveTwoGroupShape() {
        groupShape.setShapes(List.of(square));
        GroupShape groupShape2 = new GroupShape(125, 30);
        groupShape2.setxEnd(250);
        groupShape2.setyEnd(150);
        groupShape2.setShapes(List.of(triangle, groupShape));
        abstractShapes.add(groupShape2);
        abstractShapes.add(circle);
        xmlSaver = new XMLSaver(abstractShapes);
        expected = """
                <?xml version="1.0" encoding="UTF-8"?>
                <shapes>
                \t<shape><type>groupShape</type><x>100</x><y>5</y><xEnd>250</xEnd><yEnd>150</yEnd>
                \t\t<shapes>
                \t\t\t<shape><type>triangle</type><x>75</x><y>10</y></shape>
                \t\t\t<shape><type>groupShape</type><x>75</x><y>0</y><xEnd>200</xEnd><yEnd>100</yEnd>
                \t\t\t\t<shapes>
                \t\t\t\t\t<shape><type>square</type><x>75</x><y>50</y></shape>
                \t\t\t\t</shapes>
                \t\t\t</shape>
                \t\t</shapes>
                \t</shape>
                \t<shape><type>circle</type><x>100</x><y>5</y></shape>
                </shapes>""";
        xmlSaver.saveShapes();
        result = xmlSaver.getSave().toString();
        assertEquals(expected, result);
    }

}

