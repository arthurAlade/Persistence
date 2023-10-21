package edu.uga.miage.m1.polygons.gui.command;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    private final List<Command> commands;
    /* A command have multiple status :
    - WAITING : insert in commands but not executed
    - DONE : executed
    - UNDONE : executed and reversed (Ctrl - Z)
     */

    public CommandList() {
        this.commands = new ArrayList<>();
    }

    public void add(Command command) {
        commands.add(command);
    }

    public void executeLastCommand() {
        if (commands.isEmpty()){
            throw new IndexOutOfBoundsException("No command to execute");
        }
        Command commandToExecute = commands.get(commands.size() - 1);
        if(commandToExecute.getStatus() == CommandStatus.WAITING) {
            if (commandToExecute instanceof RemoveCommand && commands.size() > 1) {
                undoneCommandByIndex(commands.size() - 2);
            }
            commandToExecute.execute();
            commandToExecute.setStatus(CommandStatus.DONE);
        }
        else{
            throw new IndexOutOfBoundsException("No command to execute");
        }
    }

    public void undoneCommandByIndex(int index){
        if(index < 0 || index >= commands.size()) {
            throw new IndexOutOfBoundsException("No command to undo");
        }
        Command commandToUndo = commands.get(index);
        if(commandToUndo.getStatus() == CommandStatus.DONE) {
            if (!(commandToUndo instanceof RemoveCommand)) {
                commandToUndo.setStatus(CommandStatus.UNDONE);
            }
            else {
                undoneCommandByIndex(index - 1);
            }
        }
        else if (commandToUndo.getStatus() == CommandStatus.UNDONE){
            undoneCommandByIndex(index - 1);
        }
    }

    public Command getCommand(int index){
        return commands.get(index);
    }


}
