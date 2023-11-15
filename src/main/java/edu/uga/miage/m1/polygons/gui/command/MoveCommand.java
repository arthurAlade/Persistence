package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.awt.*;

public class MoveCommand implements Command{
    private CommandStatus status;



    private SimpleShape shape;
    private final Graphics2D g2;
    private final JDrawingFrame frame;

    private final int xFrom, yFrom;
    private int xTo, yTo;

    public MoveCommand(SimpleShape shape, Graphics2D g2, JDrawingFrame frame){
        this.shape = shape;
        this.g2 = g2;
        this.frame = frame;
        this.status = CommandStatus.WAITING;
        this.xFrom = shape.getX();
        this.yFrom = shape.getY();
        this.xTo = 0;
        this.yTo = 0;
    }



    @Override
    public boolean execute() {
        try{
            frame.printList("Start move");
            if (status == CommandStatus.PENDING){
                this.xTo = shape.getX();
                this.yTo = shape.getY();
                shape.draw(g2);
                frame.addShapeToList(shape);
            }else {
                frame.removeShape(frame.getShapesListIndex(shape));
            }
            frame.printList("End move");
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean undo() {
        try{
            shape.setX(xFrom);
            shape.setY(yFrom);
            frame.removeShape(frame.getShapesListIndex(shape));
            shape.draw(g2);
            frame.addShapeToList(shape);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public CommandStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public void setShape(SimpleShape shape) {
        this.shape = shape;
    }

}
