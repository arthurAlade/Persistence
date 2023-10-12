package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String prefixe;
    private String representation;
    private String suffixe;

    private String shapes;

    public JSonVisitor() {
        this.shapes = "";
        this.prefixe =  "{\"shapes\":[";
        this.representation = "";
        this.suffixe = "]}";
    }

    private String getTemplateFormat(String shape, int pos_x, int pos_y){
   

        return "{\n"
                + "        \"type\": \"" + shape +  "\",\n"
                + "        \"x\": " + pos_x + ",\n"
                + "        \"y\": " + pos_y + "\n"
                + "}";
    }

    @Override
    public void visit(Circle circle) {
        if(!shapes.equals("")){
            shapes+=",";
        }

        this.shapes += this.getTemplateFormat("circle", circle.getX(), circle.getY());

        this.representation = prefixe + shapes + suffixe;
        System.out.println(this.getRepresentation());
    }

    @Override
    public void visit(Square square) {
        if(!shapes.equals("")){
            shapes+=",";
        }
        this.shapes += this.getTemplateFormat("square", square.getX(), square.getY());

        this.representation = prefixe + shapes + suffixe;
        System.out.println(this.getRepresentation());
    }

    @Override
    public void visit(Triangle triangle) {
        if(!shapes.equals("")){
            shapes+=",";
        }
        this.shapes += this.getTemplateFormat("triangle", triangle.getX(), triangle.getY());

        this.representation = prefixe + shapes + suffixe;
        System.out.println(this.getRepresentation());
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
