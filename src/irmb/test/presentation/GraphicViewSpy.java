package irmb.test.presentation;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.graphics.GraphicLine;
import irmb.flowsim.view.graphics.GraphicPolyLine;
import irmb.flowsim.view.graphics.GraphicRectangle;
import irmb.flowsim.view.graphics.GraphicShape;
import irmb.util.Observer;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewSpy implements GraphicView, Observer {


    private Point first;
    private Point second;

    private boolean hasReceivedShape;
    private boolean hasReceivedLine;
    private boolean hasReceivedRectangle;
    private boolean hasReceivedPolyLine;

    private boolean wasNotified;
    private int timesNotified;

    private int timesReceivedLineCalled;

    private boolean wasLineRemoved;
    private boolean wasRectangleRemoved;
    private boolean wasPolyLineRemoved;

    private int timesRemoveLineCalled;

    protected GraphicShape receivedShape;


    private void receiveLine(GraphicLine line) {
        timesReceivedLineCalled++;
        hasReceivedLine = true;
    }

    @Override
    public void receiveShape(GraphicShape graphicShape) {
        hasReceivedShape = true;
        if (graphicShape instanceof GraphicLine)
            receiveLine((GraphicLine) graphicShape);
        else if (graphicShape instanceof GraphicRectangle)
            hasReceivedRectangle = true;
        else if (graphicShape instanceof GraphicPolyLine)
            hasReceivedPolyLine = true;
        receivedShape = graphicShape;
    }

    @Override
    public void removeShape(GraphicShape graphicShape) {
        if (graphicShape instanceof GraphicLine)
            removeLine();
        else if (graphicShape instanceof GraphicRectangle)
            removeRectangle();
        else if (graphicShape instanceof GraphicPolyLine)
            removePolyLine();
        receivedShape = graphicShape;
    }

    private void removeLine() {
        wasLineRemoved = true;
        timesRemoveLineCalled++;
    }

    private void removeRectangle() {
        wasRectangleRemoved = true;
    }

    private void removePolyLine() {
        wasPolyLineRemoved = true;
    }

    @Override
    public void update() {
        wasNotified = true;
        timesNotified++;
    }

    public boolean hasReceivedLine() {
        return hasReceivedLine;
    }

    public int getTimesReceiveLineCalled() {
        return timesReceivedLineCalled;
    }

    public boolean hasReceivedRectangle() {
        return hasReceivedRectangle;
    }

    public boolean hasReceivedPolyLine() {
        return hasReceivedPolyLine;
    }

    public boolean hasReceivedShape() {
        return hasReceivedShape;
    }

    public boolean wasNotified() {
        return wasNotified;
    }

    public int getTimesNotified() {
        return timesNotified;
    }

    public boolean wasLineRemoved() {
        return wasLineRemoved;
    }

    public boolean wasRectangleRemoved() {
        return wasRectangleRemoved;
    }

    public boolean wasPolyLineRemoved() {
        return wasPolyLineRemoved;
    }

    public int getTimesRemoveLineCalled() {
        return timesRemoveLineCalled;
    }
}
