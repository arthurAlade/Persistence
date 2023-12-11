package edu.uga.miage.m1.polygons.gui.persistence;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;

import java.util.List;

public class Saver {
    private final StringBuilder save = new StringBuilder();

    private final List<AbstractShape> visitablesList;
    private int indent;

    public Saver(List<AbstractShape> visitablesList) {
        this.visitablesList = visitablesList;
        this.indent = 0;
    }

    public void addShapes(boolean xml){
        indent++;
        Visitor visitor = xml ? new XMLVisitor() : new JSonVisitor();
        visitablesList.forEach(shape -> {
            System.out.println(shape);
            extractGroupShape(xml, shape, visitor);
            save.append(xml ? "\n" : getEndShape(visitablesList.indexOf(shape), visitablesList.size()));
        });
    }

    private void extractGroupShape(boolean xml, Visitable shape, Visitor visitor) {
        if (shape instanceof GroupShape groupShape) {
            System.out.println("GroupShape");
            addShapeFromList(shape, visitor);
            save.append("\n");
            indent++;
            List<AbstractShape> list = groupShape.getShapes();
            list.forEach(shape1 -> {
                extractGroupShape(xml, shape1, visitor);
                save.append(getEndShape(list.indexOf(shape1), list.size()));
            });
            indent--;
            save.append("\t".repeat(Math.max(0, indent)));
            save.append(xml ? "</shapes></shape>" : "/n ]}");

        }
        else {
            addShapeFromList(shape, visitor);
        }
    }

    private void addShapeFromList(Visitable shape, Visitor visitor){
            shape.accept(visitor);
            save.append("\t".repeat(Math.max(0, indent)));
            save.append(visitor.getRepresentation());
    }


    private String getEndShape(int index, int totalListSize){
        return index == totalListSize - 1 ? "\n" : ",\n";
    }

    public StringBuilder getSave() {
        return save;
    }

    public void addToSave(StringBuilder save) {
        this.save.append(save);
    }
}
