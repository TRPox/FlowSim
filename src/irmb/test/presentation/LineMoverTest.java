package irmb.test.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.LineMover;
import org.junit.Test;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;

/**
 * Created by Sven on 21.11.2016.
 */
public class LineMoverTest {
    @Test
    public void canCreateLineMover() {
        LineMover lineMover = new LineMover(new Line());
    }

    @Test
    public void whenCallingMoveBy_lineStartAndEndShouldChangeByDelta() {
        Line line = new Line();
        line.setStart(new Point(11, 12));
        line.setEnd(new Point(21, 22));
        LineMover lineMover = new LineMover(line);
        int dx = 35;
        int dy = 25;
        lineMover.moveBy(dx, dy);

        Point expectedStart = new Point(46, 37);
        Point expectedEnd = new Point(56, 47);
        assertExpectedPointEqualsActual(expectedStart, line.getStart());
        assertExpectedPointEqualsActual(expectedEnd, line.getEnd());
    }
}