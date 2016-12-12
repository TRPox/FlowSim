package irmb.test.command;

import irmb.flowsim.command.MoveShapeCommand;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import org.junit.Before;
import org.junit.Test;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;

/**
 * Created by Sven on 08.12.2016.
 */
public class MoveShapeCommandTest {

    private MoveShapeCommand sut;
    private Line line;
    private final int dx = 5;
    private final int dy = 6;

    @Before
    public void setUp() throws Exception {
        line = new Line();
        line.setStart(new Point(3, 5));
        line.setEnd(new Point(6, 3));
        sut = new MoveShapeCommand(line);
    }

    @Test
    public void canSetDelta() {
        sut.setDeltaX(5.);
        sut.setDeltaY(5.);
    }

    @Test
    public void canCallUndo() {
        sut.undo();
    }

    @Test
    public void whenCallingUndoAfterMovingShape_shouldUndoMove() {
        line.moveBy(dx, dy);
        sut.setDeltaX(dx);
        sut.setDeltaY(dy);
        sut.undo();
        assertExpectedPointEqualsActual(new Point(3, 5), line.getStart());
        assertExpectedPointEqualsActual(new Point(6, 3), line.getEnd());
    }

    @Test
    public void whenCallingExecute_shouldMoveShapeByDelta() {
        sut.setDeltaX(dx);
        sut.setDeltaY(dy);
        sut.execute();
        assertExpectedPointEqualsActual(new Point(8, 11), line.getStart());
        assertExpectedPointEqualsActual(new Point(11, 9), line.getEnd());
    }
}

