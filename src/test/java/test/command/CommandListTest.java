package test.command;

import edu.uga.miage.m1.polygons.gui.command.AddCommand;
import edu.uga.miage.m1.polygons.gui.command.CommandList;
import edu.uga.miage.m1.polygons.gui.command.CommandStatus;
import edu.uga.miage.m1.polygons.gui.command.MoveCommand;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandListTest {
    CommandList commandList;
    AddCommand addCommandDoned;
    AddCommand addCommandUndoned;

    MoveCommand moveCommand;


    @BeforeEach
    void setUp() {
        commandList = new CommandList();
        addCommandDoned = new AddCommand(null, null, null);
        addCommandDoned.setStatus(CommandStatus.DONE);

        addCommandUndoned = new AddCommand(null, null, null);
        addCommandUndoned.setStatus(CommandStatus.UNDONE);

        moveCommand = new MoveCommand(new Circle(0, 0), null, null);
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

        commandList.undoneCommandByIndex(0);
        assertEquals(CommandStatus.UNDONE, commandList.getCommand(0).getStatus());
    }

    @Test
    void undoneCommandByIndexRecursive(){
        commandList.add(addCommandDoned);
        commandList.add(addCommandUndoned);

        commandList.undoneLastCommand();

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

    @Test
    void executeMoveCommand(){
        commandList.add(moveCommand);
        commandList.executeLastCommand();
        assertEquals(CommandStatus.PENDING, commandList.getCommand(0).getStatus());
        commandList.executeLastCommand();
        assertEquals(CommandStatus.DONE, commandList.getCommand(0).getStatus());
    }









}
