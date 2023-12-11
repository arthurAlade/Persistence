package edu.uga.miage.m1.polygons.gui.persistence;

import java.io.BufferedReader;
import java.io.FileReader;

public class XMLImporter {
    public XMLImporter() {
    }

    public boolean importXML() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("save.xml"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error while reading XML file");
            return false;
        }
        return true;
    }
}
