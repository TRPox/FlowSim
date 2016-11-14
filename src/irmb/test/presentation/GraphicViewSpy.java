package irmb.test.presentation;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.GraphicLine;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.view.GraphicShape;
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

    protected GraphicShape receivedShape;


    private void receiveLine(GraphicLine line) {
        timesReceivedLineCalled++;
        hasReceivedLine = true;
        receivedShape = line;
    }

    private void receiveRectangle(GraphicRectangle rectangle) {
        hasReceivedRectangle = true;
        receivedShape = rectangle;
    }

    private void receivePolyLine(GraphicPolyLine polyLine) {
        hasReceivedPolyLine = true;
        receivedShape = polyLine;
    }

    @Override
    public void receiveShape(GraphicShape graphicShape) {
        hasReceivedShape = true;
        if (graphicShape instanceof GraphicLine)
            receiveLine((GraphicLine) graphicShape);
        else if (graphicShape instanceof GraphicRectangle)
            receiveRectangle((GraphicRectangle) graphicShape);
        else if (graphicShape instanceof GraphicPolyLine)
            receivePolyLine((GraphicPolyLine) graphicShape);
    }

    @Override
    public void removeShape(GraphicShape graphicShape) {
        if (graphicShape instanceof GraphicLine)
            wasLineRemoved = true;
        else if (graphicShape instanceof GraphicRectangle)
            wasRectangleRemoved = true;
        else if (graphicShape instanceof GraphicPolyLine)
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

    public int getTimesReceivedLineCalled() {
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
}
