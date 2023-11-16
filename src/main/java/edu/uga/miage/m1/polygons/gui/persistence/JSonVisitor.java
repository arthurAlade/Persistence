package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor extends Visitor {


    public JSonVisitor() {
        super();
    }

    public String getSchema(int x, int y, String type) {

        return "{\n\"type\": \"" + type + "\",\n\"x\": " + x + ",\n\"y\": " + y + "\n}";
    }

    public String getSchema(int xStart, int yStart, int xEnd, int yEnd, String type){
            return "{\n\"type\": \"" + type + "\",\n\"xStart\": " + xStart + ",\n\"yStart\": " + yStart + "\",\n\"xEnd\": " + xEnd + ",\n\"yEnd\": " + yEnd +  ",\n\"shapes\": [\n";
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

}