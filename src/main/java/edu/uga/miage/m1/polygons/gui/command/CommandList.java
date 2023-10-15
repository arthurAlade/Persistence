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

    private final List<CommandStatus> commandStatusList;
    public CommandList() {
        this.commands = new ArrayList<>();
        this.commandStatusList = new ArrayList<>();
    }

    public void add(Command command) {
        commands.add(command);
        commandStatusList.add(CommandStatus.WAITING);
    }

    public void executeLastCommand() {
        if( (!commands.isEmpty()) && (commandStatusList.get(commandStatusList.size() - 1) == CommandStatus.WAITING)  ) {
            Command commandToExecute = commands.get(commands.size() - 1);
            commandToExecute.execute();
            if(commandToExecute instanceof RemoveCommand){
                undoneLastCommand();
                rerunCommands();
            }
            commandStatusList.set(commandStatusList.size()-1, CommandStatus.DONE);
        }
        else {
            throw new IndexOutOfBoundsException("No command to execute");
        }
    }

    public void undoneLastCommand(){
        if(commands.size() == 1) {
            throw new IndexOutOfBoundsException("No command to undo");
        }else {
            commandStatusList.set(commandStatusList.size()-2, CommandStatus.UNDONE);
        }
    }

    private void rerunCommands(){
        for(int i = 0; i < commands.size() - 1; i++){
            Command commandToExecute = commands.get(i);
            CommandStatus commandToExecuteStatus = commandStatusList.get(i);
            System.out.println(commandToExecute.getClass().getName() + ' ' + commandToExecuteStatus);
            if(commandToExecuteStatus==CommandStatus.DONE){
                commandToExecute.execute();
            }
        }
    }
}
