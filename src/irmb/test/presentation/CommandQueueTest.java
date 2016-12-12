package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.presentation.CommandQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Sven on 12.12.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class CommandQueueTest extends CommandQueue {

    private CommandQueueTest sut;

    @Before
    public void setUp() throws Exception {
        sut = new CommandQueueTest();
    }

    @Test
    public void canAddCommand() {
        CommandSpy command = new CommandSpy();
        sut.add(command);
        assertTrue(sut.commandList.contains(command));
    }

    @Test
    public void whenCallingUndo_shouldDoNothing() {
        sut.undo();
    }

    @Test
    public void whenCallingRedo_shouldDoNothing() {
        sut.redo();
    }

    public class OneCommandAddedContext {

        private CommandSpy firstCommand;

        @Before
        public void setUp() {
            firstCommand = new CommandSpy();
            sut.add(firstCommand);
        }

        @Test
        public void whenCallingUndo_shouldUndoCommand() {
            sut.undo();
            assertTrue(firstCommand.wasUndoCalled());
        }

        @Test
        public void whenCallingUndoTwice_shouldOnlyUndoFirstCommand() {
            sut.undo();
            sut.undo();
            assertTrue(firstCommand.wasUndoCalled());
        }

        @Test
        public void whenCallingRedo_shouldDoNothing() {
            sut.redo();
        }

        public class TwoCommandsAddedContext {

            private CommandSpy secondCommand;

            @Before
            public void setUp() {
                secondCommand = new CommandSpy();
                sut.add(secondCommand);
            }

            @Test
            public void whenCallingUndo_shouldUndoSecondCommand() {
                sut.undo();
                assertTrue(secondCommand.wasUndoCalled());
            }

            @Test
            public void whenCallingUndoTwice_shouldUndoBothCommands() {
                sut.undo();
                assertTrue(secondCommand.wasUndoCalled());
                sut.undo();
                assertTrue(firstCommand.wasUndoCalled());
            }

            public class CalledUndoOnceContext {

                private final CommandSpy thirdCommand = new CommandSpy();

                @Before
                public void setUp() {
                    sut.undo();
                }

                @Test
                public void whenCallingRedo_shouldRedoSecondCommand() {
                    sut.redo();
                    assertTrue(secondCommand.wasRedoCalled());
                }

                @Test
                public void whenAddingNewCommand_queueShouldOnlyContainFirstAndThirdCommand() {
                    sut.add(thirdCommand);

                    assertFalse(sut.commandList.contains(secondCommand));
                    assertTrue(sut.commandList.contains(firstCommand));
                    assertTrue(sut.commandList.contains(thirdCommand));
                }

                public class CalledUndoTwiceContext {
                    @Before
                    public void setUp() {
                        sut.undo();
                    }

                    @Test
                    public void whenCallingRedo_shouldRedoFirstCommand() {
                        sut.redo();
                        assertTrue(firstCommand.wasRedoCalled());
                    }

                    @Test
                    public void whenAddingNewCommand_queueShouldOnlyContainNewCommand() {
                        sut.add(thirdCommand);
                        assertTrue(sut.commandList.contains(thirdCommand));
                        assertEquals(1, sut.commandList.size());
                        assertFalse(sut.commandList.contains(firstCommand));
                        assertFalse(sut.commandList.contains(secondCommand));
                    }

                }
            }
        }
    }


}