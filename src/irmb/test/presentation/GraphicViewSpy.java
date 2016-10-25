package irmb.test.presentation;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.GraphicView;

import java.util.List;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewSpy implements GraphicView {


    private Point first;
    private Point second;

    private boolean hasReceivedLine;
    private int timesReceivedLineCalled;
    private boolean hasReceivedRectangle;
    private boolean hasReceivedPolyLine;
    private List<Point> receivedPolyLinePointList;
    private boolean wasNotified;

    private void receiveLine(Line line) {
        timesReceivedLineCalled++;
        hasReceivedLine = true;
        this.first = line.getStart();
        this.second = line.getEnd();
    }

    private void receiveRectangle(Rectangle rectangle) {
        hasReceivedRectangle = true;
        this.first = rectangle.getFirst();
        this.second = rectangle.getSecond();
    }

    private void receivePolyLine(PolyLine polyLine) {
        hasReceivedPolyLine = true;
        receivedPolyLinePointList = polyLine.getPointList();
    }

    @Override
    public void receiveShape(Shape shape) {
        if (shape instanceof Line)
            receiveLine((Line) shape);
        else if (shape instanceof Rectangle)
            receiveRectangle((Rectangle) shape);
        else if (shape instanceof PolyLine)
            receivePolyLine((PolyLine) shape);
    }

    @Override
    public void update() {
        wasNotified = true;
    }

    public Point getFirst() {
        return first;
    }

    public Point getSecond() {
        return second;
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

    public List<Point> getReceivedPolyLinePointList() {
        return receivedPolyLinePointList;
    }

    public boolean wasNotified() {
        return wasNotified;
    }
}
