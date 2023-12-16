package edu.uga.miage.m1.polygons.gui.persistence;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

import java.util.List;
public class JSONSaverAdapter implements SaverAdapterInterface{
    private JSONSaver jsonSaver;

    public JSONSaverAdapter(List<AbstractShape> shapes) {
        jsonSaver = new JSONSaver(shapes);
    }

    @Override
    public boolean save() {
        jsonSaver.saveShapes();
        return jsonSaver.saveJSON();
    }
}
