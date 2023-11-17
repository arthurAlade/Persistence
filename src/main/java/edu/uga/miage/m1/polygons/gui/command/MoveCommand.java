package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;

import java.awt.*;

public class MoveCommand implements Command{
    private CommandStatus status;

    private final AbstractShape shape;
    private final Graphics2D g2;
    private final JDrawingFrame frame;

    private final int xFrom;
    private final int yFrom;


    public MoveCommand(AbstractShape shape, Graphics2D g2, JDrawingFrame frame){
        this.shape = shape;
        this.g2 = g2;
        this.frame = frame;
        this.status = CommandStatus.WAITING;
        this.xFrom = shape.getX();
        this.yFrom = shape.getY();
    }



    @Override
    public boolean execute() {
        try{
            if (status == CommandStatus.PENDING){
                shape.draw(g2);
                if (shape instanceof GroupShape groupShape){
                    groupShape.getShapes().forEach(shape1 -> shape1.draw(g2) );
                }
                frame.addShapeToList(shape);
            }else {
                frame.removeShape(frame.getShapesListIndex(shape));
            }
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


}
