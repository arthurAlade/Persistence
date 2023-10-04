package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String prefixe = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<root><shapes>";
    private String representation = null;
    private String suffixe = "</shapes></root>";

    private String shapes="";
    public XMLVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        this.shapes += "<shape>"
                + "<type>circle</type>"
                + "<x>"+circle.getX()+"</x>"
                + "<y>"+circle.getY()+"</y>"
                + "</shape>";
        this.representation = prefixe + shapes + suffixe;
    }

    @Override
    public void visit(Square square) {
        this.shapes += "<shape>"
                + "<type>square</type>"
                + "<x>"+square.getX()+"</x>"
                + "<y>"+square.getY()+"</y>"
                + "</shape>";

        this.representation = prefixe + shapes + suffixe;
    }

    @Override
    public void visit(Triangle triangle) {
        this.shapes += "<shape>"
                + "<type>triangle</type>"
                + "<x>"+triangle.getX()+"</x>"
                + "<y>"+triangle.getY()+"</y>"
                + "</shape>";
        this.representation = prefixe + shapes + suffixe;
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
