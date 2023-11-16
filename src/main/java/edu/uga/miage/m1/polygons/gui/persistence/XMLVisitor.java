package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor extends Visitor {


    public XMLVisitor() {
        super();
    }

    public String getSchema(int x, int y, String type) {
        return "<shape><type>" + type + "</type><x>" + x + "</x><y>" + y + "</y></shape>";
    }

    @Override
    String getSchema(int xStart, int yStart, int xEnd, int yEnd, String type) {
        return "<shape><type>" + type + "</type><x>" + xStart + "</x><y>" + yStart + "</y><xEnd>" + xEnd + "</xEnd><yEnd>" + yEnd + "</yEnd><shapes>";
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

}