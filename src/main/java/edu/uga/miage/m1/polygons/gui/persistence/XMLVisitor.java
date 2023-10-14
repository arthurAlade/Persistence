package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String prefixe ;
    private String representation;
    private String suffixe ;

    private String shapes="";
    public XMLVisitor() {
        this.prefixe = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<root><shapes>";
        this.representation = null;
        this.suffixe = "</shapes></root>";

    }

    private String getTemplateFormat(String shape, int pos_x, int pos_y){

        return "<shape>"
                + "<type>"+ shape +"</type>"
                + "<x>"+pos_x+"</x>"
                + "<y>"+pos_y+"</y>"
                + "</shape>";
    }

    @Override
    public void visit(Circle circle) {

        this.shapes += getTemplateFormat("circle", circle.getX(), circle.getY());
        this.representation = prefixe + shapes + suffixe;
        System.out.println(this.getRepresentation());
    }

    @Override
    public void visit(Square square) {
        this.shapes += getTemplateFormat("square", square.getX(), square.getY());
        this.representation = prefixe + shapes + suffixe;
        System.out.println(this.getRepresentation());
    }

    @Override
    public void visit(Triangle triangle) {
        this.shapes += getTemplateFormat("triangle", triangle.getX(), triangle.getY());
        this.representation = prefixe + shapes + suffixe;
        System.out.println(this.getRepresentation());
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
