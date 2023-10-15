package edu.uga.miage.m1.polygons.gui.persistence;
import java.util.List;

public class Saver {
    private final StringBuilder save = new StringBuilder();

    private final int totalListSize;
    private int index = 0;
    private final List<Visitable> visitablesList;

    public Saver(List<Visitable> visitablesList) {
        this.visitablesList = visitablesList;
        this.totalListSize = visitablesList.size();

    }

    public void addShapes(boolean xml){
        Visitor visitor = xml ? new XMLVisitor() : new JSonVisitor();
        visitablesList.forEach(element -> {
            element.accept(visitor);
            save.append(visitor.getRepresentation());
            save.append(xml ? "\n" : getEndShape());
            index++;
        });
    }


    private String getEndShape(){
        return index == totalListSize - 1 ? "\n" : ",\n";
    }

    public StringBuilder getSave() {
        return save;
    }

    public void addToSave(StringBuilder save) {
        this.save.append(save);
    }
}
