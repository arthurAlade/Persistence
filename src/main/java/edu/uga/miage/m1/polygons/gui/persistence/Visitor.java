package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * You must define a method for each type of Visitable.
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public abstract class Visitor {
    private String representation;

    protected Visitor() {
        this.representation = null;
    }

    public void visit(Circle circle) {
        this.representation = getSchema(circle.getX(), circle.getY(), "circle");

    }

    public void visit(Square square) {
        this.representation = getSchema(square.getX(), square.getY(), "square");
    }
    
    public void visit(Cube cube) {
        representation = getSchema(cube.getX(), cube.getY(), "cube");
    }

    public void visit(Triangle triangle) {
        representation = getSchema(triangle.getX(), triangle.getY(), "triangle");
    }

    public void visit(GroupShape group) {
        representation = getSchema(group.getX(), group.getY(), group.getxEnd(), group.getyEnd(), "groupShape");
    }

    public String getRepresentation() {
        return representation;
    }

    abstract String getSchema(int x, int y, String type);
    abstract String getSchema(int xStart, int yStart, int xEnd, int yEnd, String type);
}
