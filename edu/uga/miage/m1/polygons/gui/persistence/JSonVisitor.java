package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;

    public JSonVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        this.representation = "{\n"
                + "        \"type\": \"circle\",\n"
                + "        \"x\": " + circle.getX() + ",\n"
                + "        \"y\": " + circle.getY() + "\n"
                + "}";

    }

    @Override
    public void visit(Square square) {
        this.representation = "{\n"
                + "        \"type\": \"square\",\n"
                + "        \"x\": " + square.getX() + ",\n"
                + "        \"y\": " + square.getY() + "\n"
                + "}";
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation ="{\n"
                + "        \"type\": \"triangle\",\n"
                + "        \"x\": " + triangle.getX() + ",\n"
                + "        \"y\": " + triangle.getY() + "\n"
                + "}";
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
