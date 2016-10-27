package irmb.test.presentation;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.GraphicLine;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.view.GraphicShape;
import irmb.util.Observer;

import java.util.List;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewSpy implements GraphicView, Observer {


    private Point first;
    private Point second;

    private boolean hasReceivedLine;
    private int timesReceivedLineCalled;
    private boolean hasReceivedRectangle;
    private boolean hasReceivedPolyLine;
    private List<Point> receivedPolyLinePointList;
    private boolean wasNotified;

    private void receiveLine(GraphicLine line) {
        timesReceivedLineCalled++;
        hasReceivedLine = true;
        this.first = line.getStart();
        this.second = line.getEnd();
    }

    private void receiveRectangle(GraphicRectangle rectangle) {
        hasReceivedRectangle = true;
        this.first = rectangle.getFirst();
        this.second = rectangle.getSecond();
    }

    private void receivePolyLine(GraphicPolyLine polyLine) {
        hasReceivedPolyLine = true;
        receivedPolyLinePointList = polyLine.getPointList();
    }

    @Override
    public void receiveShape(GraphicShape graphicShape) {
        if (graphicShape instanceof GraphicLine)
            receiveLine((GraphicLine) graphicShape);
        else if (graphicShape instanceof GraphicRectangle)
            receiveRectangle((GraphicRectangle) graphicShape);
        else if (graphicShape instanceof GraphicPolyLine)
            receivePolyLine((GraphicPolyLine) graphicShape);
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
