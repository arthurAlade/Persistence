package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

import java.util.List;

public class XMLSaverAdapter implements SaverAdapterInterface {
    private XMLSaver xmlSaver;

    public XMLSaverAdapter(List<AbstractShape> shapes) {
        xmlSaver = new XMLSaver(shapes);
    }

    @Override
    public boolean save() {
        xmlSaver.saveShapes();
        return xmlSaver.saveXML();
    }


}
