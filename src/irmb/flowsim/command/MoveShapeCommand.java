package irmb.flowsim.command;

import irmb.flowsim.model.geometry.Shape;

/**
 * Created by Sven on 08.12.2016.
 */
public class MoveShapeCommand {

    private final Shape shape;
    private double deltaX;
    private double deltaY;

    public MoveShapeCommand(Shape shape) {
        this.shape = shape;
    }


    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public void execute() {
        shape.moveBy(deltaX, deltaY);
    }

    public void undo() {
        shape.moveBy(-deltaX, -deltaY);
    }
}
