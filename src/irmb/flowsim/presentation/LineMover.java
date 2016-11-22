package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;

/**
 * Created by Sven on 21.11.2016.
 */
public class LineMover {

    private Line line;

    public LineMover(Line line) {
        this.line = line;
    }


    public void moveBy(int dx, int dy) {
        Point newStart = new Point(line.getStart().getX() + dx, line.getStart().getY() + dy);
        Point newEnd = new Point(line.getEnd().getX() + dx, line.getEnd().getY() + dy);

        line.setStart(newStart);
        line.setEnd(newEnd);
    }


}
