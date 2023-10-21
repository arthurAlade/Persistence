package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public class RemoveCommand implements Command{
    private JDrawingFrame frame;
    private CommandStatus status;

    public RemoveCommand(JDrawingFrame frame) {
        this.frame = frame;
        this.status = CommandStatus.WAITING;
    }


    @Override
    public void execute() {
        frame.removeLastShape();
    }

    public CommandStatus getStatus(){
        return this.status;
    }

    public void setStatus(CommandStatus status){
        this.status = status;
    }

}
