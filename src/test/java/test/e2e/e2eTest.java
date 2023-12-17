package test.e2e;

import javax.swing.JButton;
import javax.swing.JFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



class e2e {


        private JDrawingFrame jFrame;




        @BeforeEach
        void setUp() {
                JFrame frame = new JDrawingFrame("testing");
                WindowAdapter wa = new WindowAdapter() {

                        @Override
                        public void windowClosing(WindowEvent e) {
                                System.exit(0);
                        }
                };
                frame.addWindowListener(wa);
                frame.pack();

                frame.setVisible(true);

                jFrame = new JDrawingFrame("test");



        }

        private String readFromFile(String filePath) throws IOException {
                Path path = Paths.get(filePath);
                return new String(Files.readAllBytes(path));
        }


        @Test()
        void addASquare() throws IOException {
                Graphics2D g2 = (Graphics2D) jFrame.getmPanel().getGraphics();
                Square square = new Square(50, 50);
                jFrame.addShape(square, g2);


                jFrame.getmJsonButton().doClick();
                jFrame.getmXmlButton().doClick();

                String expectedJsonContent = "{\"shapes\":[\n" + "\t{\n" + "\"type\": \"square\",\n"
                                + "\"x\": 25,\n" + "\"y\": 25\n" + "}\n" + "]}";

                String expectedXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"
                                + "\t<shape><type>square</type><x>25</x><y>25</y></shape>\n"
                                + "</shapes>";


                String actualJsonContent = readFromFile("./save.json");
                String actualXMLContent = readFromFile("./save.xml");


                assertEquals(expectedJsonContent, actualJsonContent);
                assertEquals(expectedXMLContent, actualXMLContent);
        }


        @Test()
        void addACircle() throws IOException, AWTException {
                Graphics2D g2 = (Graphics2D) jFrame.getmPanel().getGraphics();
                Circle circle = new Circle(50, 50);
                jFrame.addShape(circle, g2);

                jFrame.getmJsonButton().doClick();
                jFrame.getmXmlButton().doClick();


                String expectedJsonContent = "{\"shapes\":[\n" + "\t{\n" + "\"type\": \"circle\",\n"
                                + "\"x\": 25,\n" + "\"y\": 25\n" + "}\n" + "]}";

                String expectedXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"
                                + "\t<shape><type>circle</type><x>25</x><y>25</y></shape>\n"
                                + "</shapes>";


                String actualJsonContent = readFromFile("./save.json");
                String actualXMLContent = readFromFile("./save.xml");


                assertEquals(expectedJsonContent, actualJsonContent);
                assertEquals(expectedXMLContent, actualXMLContent);
        }


        @Test()
        void addATriangle() throws IOException {
                Graphics2D g2 = (Graphics2D) jFrame.getmPanel().getGraphics();
                Triangle triangle = new Triangle(75, 75);
                jFrame.addShape(triangle, g2);
                jFrame.getmJsonButton().doClick();


                jFrame.getmXmlButton().doClick();



                String expectedJsonContent = "{\"shapes\":[\n" + "\t{\n" + "\"type\": \"triangle\",\n"
                                + "\"x\": " + triangle.getX() + ",\n" + "\"y\": " + triangle.getY()
                                + "\n" + "}\n" + "]}";

                String expectedXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"
                                + "\t<shape><type>triangle</type><x>" + triangle.getX() + "</x><y>"
                                + triangle.getY() + "</y></shape>\n" + "</shapes>";


                String actualJsonContent = readFromFile("./save.json");
                String actualXMLContent = readFromFile("./save.xml");


                assertEquals(expectedJsonContent, actualJsonContent);
                assertEquals(expectedXMLContent, actualXMLContent);
        }


        @Test()
        void addACube() throws IOException {
                Graphics2D g2 = (Graphics2D) jFrame.getmPanel().getGraphics();
                Cube cube = new Cube(75, 75);
                jFrame.addShape(cube, g2);


                jFrame.getmJsonButton().doClick();
                jFrame.getmXmlButton().doClick();



                String expectedJsonContent = "{\"shapes\":[\n" + "\t{\n" + "\"type\": \"cube\",\n"
                                + "\"x\": " + cube.getX() + ",\n" + "\"y\": " + cube.getY() + ",\n"
                                + "\"size\": " + "100\n" + "}\n" + "]}";

                String expectedXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"
                                + "\t<shape><type>cube</type><x>" + cube.getX() + "</x><y>"
                                + cube.getY() + "</y><size>100</size></shape>\n" + "</shapes>";


                String actualJsonContent = readFromFile("./save.json");
                String actualXMLContent = readFromFile("./save.xml");


                assertEquals(expectedJsonContent, actualJsonContent);
                assertEquals(expectedXMLContent, actualXMLContent);
        }

        @Test()
        void addAShapeThenUndo() throws IOException {
                Graphics2D g2 = (Graphics2D) jFrame.getmPanel().getGraphics();
                Circle circle = new Circle(75, 75);


                jFrame.addShape(circle, g2);

                // Create a KeyEvent simulating Ctrl+Z
                KeyEvent keyEvent = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED,
                                System.currentTimeMillis(), KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_Z,
                                'Z');

                // Call the keyPressed method with the simulated KeyEvent
                jFrame.keyPressed(keyEvent);

                jFrame.getmJsonButton().doClick();


                jFrame.getmXmlButton().doClick();

                String expectedJsonContent = "{\"shapes\":[\n" + "]}";

                String expectedXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>\n"
                                + "</shapes>";

                String actualJsonContent = readFromFile("./save.json");
                String actualXMLContent = readFromFile("./save.xml");

                assertEquals(expectedJsonContent, actualJsonContent);
                assertEquals(expectedXMLContent, actualXMLContent);

        }


}
