package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public class RemoveCommand implements Command{
    private JDrawingFrame frame;

    public RemoveCommand(JDrawingFrame frame) {
        this.frame = frame;
    }


    @Override
    public void execute() {
        frame.removeLastShape();
    }

}
