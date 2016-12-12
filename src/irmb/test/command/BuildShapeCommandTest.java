package irmb.test.command;

import irmb.flowsim.command.BuildShapeCommand;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicShapeRepository;
import irmb.test.presentation.GraphicViewSpyForLine;
import irmb.test.view.graphics.GraphicLineSpy;
import org.junit.Before;
import org.junit.Test;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 12.12.2016.
 */
public class BuildShapeCommandTest {

    private BuildShapeCommand sut;
    private GraphicShapeRepository repository;
    private GraphicViewSpyForLine graphicViewSpyForLine;
    private GraphicLineSpy graphicLineSpy;

    @Before
    public void setUp() {
        repository = new GraphicShapeRepository();
        graphicViewSpyForLine = new GraphicViewSpyForLine();
        Line line = new Line();
        line.setStart(new Point(4, 6));
        line.setEnd(new Point(3, 4));
        graphicLineSpy = new GraphicLineSpy(line);
        sut = new BuildShapeCommand(graphicLineSpy, graphicViewSpyForLine, repository);

    }

    @Test
    public void whenCallingExecute_viewAndRepositoryShouldReceiveShape() {
        sut.execute();
        assertTrue(graphicViewSpyForLine.hasReceivedLine());
        assertExpectedPointEqualsActual(new Point(4, 6), graphicViewSpyForLine.getFirst());
        assertExpectedPointEqualsActual(new Point(3, 4), graphicViewSpyForLine.getSecond());

        assertTrue(repository.getGraphicShapeList().contains(graphicLineSpy));
    }

    @Test
    public void whenCallingUndo_shouldRemoveGraphicLineFromViewAndRepository() {
        sut.execute();
        sut.undo();

        assertTrue(graphicViewSpyForLine.wasLineRemoved());
        assertExpectedPointEqualsActual(new Point(4, 6), graphicViewSpyForLine.getFirst());
        assertExpectedPointEqualsActual(new Point(3, 4), graphicViewSpyForLine.getSecond());

        assertFalse(repository.getGraphicShapeList().contains(graphicLineSpy));
    }

    @Test
    public void whenCallingRedo_shouldAddGraphicShapeAgain() {
        sut.execute();
        sut.undo();
        sut.redo();

        assertTrue(graphicViewSpyForLine.hasReceivedLine());
        assertExpectedPointEqualsActual(new Point(4, 6), graphicViewSpyForLine.getFirst());
        assertExpectedPointEqualsActual(new Point(3, 4), graphicViewSpyForLine.getSecond());

        assertTrue(repository.getGraphicShapeList().contains(graphicLineSpy));
    }
}