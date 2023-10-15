package edu.uga.miage.m1.polygons.gui.command;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    private List<Command> commands;

    public CommandList() {
        this.commands = new ArrayList<>();
    }

    public void add(Command command) {
        this.commands.add(command);
    }

    public void executeLastCommand() {
        if(!this.commands.isEmpty()) {
            this.commands.get(this.commands.size() - 1).execute();
        }
        else {
            throw new IndexOutOfBoundsException("No command to execute");
        }
    }
}
