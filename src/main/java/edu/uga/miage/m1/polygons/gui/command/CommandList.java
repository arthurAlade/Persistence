package edu.uga.miage.m1.polygons.gui.command;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    private final List<Command> commands;
    /* A command have multiple status :
    - WAITING : insert in commands but not executed
    - DONE : executed
    - UNDONE : executed and reversed (Ctrl - Z)
    - PENDING : command is started but not finished
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

            commandToExecute.execute();

            if (commandToExecute instanceof MoveCommand){
                commandToExecute.setStatus(CommandStatus.PENDING);
            }else{
                commandToExecute.setStatus(CommandStatus.DONE);
            }
        } else if (commandToExecute.getStatus() == CommandStatus.PENDING) {
            commandToExecute.execute();
            commandToExecute.setStatus(CommandStatus.DONE);
        } else{
            throw new IndexOutOfBoundsException("No command to execute");
        }
    }

    public void undoneLastCommand(){
        undoneCommandByIndex(commands.size() - 1);
    }

    public void undoneCommandByIndex(int index){
        if(index < 0 || index >= commands.size()) {
            throw new IndexOutOfBoundsException("No command to undo");
        }
        Command commandToUndo = commands.get(index);
        CommandStatus status = commandToUndo.getStatus();
        switch (status){
            case DONE, PENDING:
                commandToUndo.undo();
                commandToUndo.setStatus(CommandStatus.UNDONE);
                break;
            case UNDONE:
                undoneCommandByIndex(index - 1);
                break;
            case WAITING:
                throw new IndexOutOfBoundsException("No command to undo");
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    public Command getCommand(int index){
        return commands.get(index);
    }

    public int size(){
        return commands.size();
    }


}
