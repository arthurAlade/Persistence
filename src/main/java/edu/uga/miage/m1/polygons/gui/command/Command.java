package edu.uga.miage.m1.polygons.gui.command;

public interface Command {

    boolean execute();
    boolean undo();

    CommandStatus getStatus();
    void setStatus(CommandStatus status);
}

