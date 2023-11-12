package edu.uga.miage.m1.polygons.gui.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class JSONSaver extends Saver {
    private final Logger logger = Logger.getLogger(JSONSaver.class.getName());

    public JSONSaver(List<Visitable> visitablesList) {
        super(visitablesList);
    }

    public void saveShapes() {
        addToSave(new StringBuilder("{\"shapes\":[\n"));
        addShapes(false);
        addToSave(new StringBuilder("]}"));
    }

    public boolean saveJSON() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.json"))) {
            writer.write(String.valueOf(getSave()));
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error while saving JSON file", e);
            return false;
        }
        return true;
    }
}
