package test.command;

import edu.uga.miage.m1.polygons.gui.command.AddCommand;
import edu.uga.miage.m1.polygons.gui.command.CommandList;
import edu.uga.miage.m1.polygons.gui.command.CommandStatus;
import edu.uga.miage.m1.polygons.gui.command.RemoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandListTest {
    CommandList commandList;
    AddCommand addCommandDoned;
    AddCommand addCommandUndoned;
    RemoveCommand removeCommand;

    @BeforeEach
    void setUp() {
        commandList = new CommandList();
        addCommandDoned = new AddCommand(null, null);
        addCommandDoned.setStatus(CommandStatus.DONE);
        removeCommand = new RemoveCommand(null);

        addCommandUndoned = new AddCommand(null, null);
        addCommandUndoned.setStatus(CommandStatus.UNDONE);
    }

    @Test
    void undoneCommandByIndexThrowsErrorIfIndexIs0(){
        CommandList commandEmptyList = new CommandList();
         assertThrows(IndexOutOfBoundsException.class, () -> commandEmptyList.undoneCommandByIndex(-1));
    }

    @Test
    void undoneCommandByIndexThrowsErrorIfIndexIsHigherThanListSize(){
        CommandList commandListError = new CommandList();
        commandListError.add(addCommandDoned);

         assertThrows(IndexOutOfBoundsException.class, () -> commandListError.undoneCommandByIndex(1));
    }

    @Test
    void undoneCommandByIndex(){
        commandList.add(addCommandDoned);
        commandList.add(removeCommand);

        commandList.undoneCommandByIndex(0);
        assertEquals(CommandStatus.UNDONE, commandList.getCommand(0).getStatus());
    }

    @Test
    void undoneCommandByIndexRecursive(){
        commandList.add(addCommandDoned);
        commandList.add(addCommandUndoned);
        removeCommand.setStatus(CommandStatus.DONE);
        commandList.add(removeCommand);

        RemoveCommand remove = new RemoveCommand(null);
        commandList.add(remove);
        commandList.undoneCommandByIndex(2);

        assertEquals(CommandStatus.UNDONE, commandList.getCommand(0).getStatus());
    }

    @Test
    void executeLastCommandThrowsErrorIfListIsEmpty(){
        CommandList commandEmptyList = new CommandList();
        assertThrows(IndexOutOfBoundsException.class, commandEmptyList::executeLastCommand);
    }

    @Test
    void executeLastCommandThrowsErrorIfLastCommandIsUndone(){
        commandList.add(addCommandUndoned);
        assertThrows(IndexOutOfBoundsException.class, commandList::executeLastCommand);
    }



    



}
