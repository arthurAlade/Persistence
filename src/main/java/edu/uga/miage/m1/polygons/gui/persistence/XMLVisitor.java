package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation;

    public XMLVisitor() {
        this.representation = null;
    }

    public String getSchema(int x, int y, String type) {
        if (type.equals("cube")){
            return "<shape><type>" + type + "</type><x>" + x + "</x><y>" + y + "</y><size>100</size></shape>";
    
        }
        return "<shape><type>" + type + "</type><x>" + x + "</x><y>" + y + "</y></shape>";
    }
    
    @Override
    public void visit(Circle circle) {
        this.representation = getSchema(circle.getX(), circle.getY(), "circle");
    }
    @Override
    public void visit(Cube cube) {
        this.representation = getSchema(cube.getX(), cube.getY(), "cube");

    }

    @Override
    public void visit(Square square) {
        this.representation = getSchema(square.getX(), square.getY(), "square");
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation = getSchema(triangle.getX(), triangle.getY(), "triangle");
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