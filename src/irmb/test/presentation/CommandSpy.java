package irmb.test.presentation;

import irmb.flowsim.command.Command;

/**
 * Created by Sven on 12.12.2016.
 */
public class CommandSpy implements Command {


    private boolean executeCalled;
    private boolean undoCalled;
    private boolean redoCalled;

    @Override
    public void execute() {
        executeCalled = true;
    }

    @Override
    public void undo() {
        undoCalled = true;
    }

    @Override
    public void redo() {
        redoCalled = true;
    }

    public boolean wasExecuteCalled() {
        return executeCalled;
    }

    public boolean wasUndoCalled() {
        return undoCalled;
    }

    public boolean wasRedoCalled() {
        return redoCalled;
    }
}
