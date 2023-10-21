package edu.uga.miage.m1.polygons.gui.command;

public interface Command {

    void execute();
    CommandStatus getStatus();
    void setStatus(CommandStatus status);
}

