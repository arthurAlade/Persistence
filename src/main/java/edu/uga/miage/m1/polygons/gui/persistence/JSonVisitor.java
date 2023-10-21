package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation;

    public JSonVisitor() {
        this.representation = null;
    }

    public String getSchema(int x, int y, String type) {
        return "{\n\"type\": \"" + type + "\",\n\"x\": " + x + ",\n\"y\": " + y + "\n}";
    }
    @Override
    public void visit(Circle circle) {
        this.representation = getSchema(circle.x(), circle.y(), "circle");

    }

    @Override
    public void visit(Square square) {
        this.representation = getSchema(square.x(), square.y(), "square");
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation = getSchema(triangle.x(), triangle.y(), "triangle");
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}