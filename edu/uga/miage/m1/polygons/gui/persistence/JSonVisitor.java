package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String prefixe = "{\"shapes\":[";
    private String representation = "";
    private String suffixe = "]}";

    private String shapes = "";

    public JSonVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        if(shapes!= ""){
            shapes+=",";
        }

        this.shapes += "{\n"
                + "        \"type\": \"circle\",\n"
                + "        \"x\": " + circle.getX() + ",\n"
                + "        \"y\": " + circle.getY() + "\n"
                + "}";

        this.representation = prefixe + shapes + suffixe;
    }

    @Override
    public void visit(Square square) {
        if(shapes!= ""){
            shapes+=",";
        }
        this.shapes += "{\n"
                + "        \"type\": \"square\",\n"
                + "        \"x\": " + square.getX() + ",\n"
                + "        \"y\": " + square.getY() + "\n"
                + "}";

        this.representation = prefixe + shapes + suffixe;
    }

    @Override
    public void visit(Triangle triangle) {
        if(shapes!= ""){
            shapes+=",";
        }
        this.shapes += "{\n"
                + "        \"type\": \"triangle\",\n"
                + "        \"x\": " + triangle.getX() + ",\n"
                + "        \"y\": " + triangle.getY() + "\n"
                + "}";

        this.representation = prefixe + shapes + suffixe;
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
