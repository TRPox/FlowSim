package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicShapeRepository;
import irmb.flowsim.view.graphics.GraphicLine;
import irmb.flowsim.view.graphics.GraphicShape;
import irmb.test.view.graphics.GraphicLineSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven on 21.11.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicShapeRepositoryTest {

    private GraphicShapeRepository sut;

    @Before
    public void setUp() throws Exception {
        sut = new GraphicShapeRepository();
    }

    @Test
    public void canCallAddWithShape() {
        sut.add(new GraphicLine(new Line()));
    }

    @Test
    public void canCallGetShapeList() {
        sut.getGraphicShapeList();
    }

    @Test
    public void whenAddingGraphicShape_graphicShapeListShouldContainGraphicShape() {
        GraphicLine graphicShape = new GraphicLine(new Line());
        sut.add(graphicShape);
        assertTrue(sut.getGraphicShapeList().contains(graphicShape));
    }

    @Test
    public void canCallRemoveWithGraphicShape() {
        sut.remove(new GraphicLine(new Line()));
    }

    @Test
    public void canCallRemoveWithIndex() {
        sut.remove(0);
    }

    @Test
    public void whenCallingGetGraphicShapeAt_shouldReturnNull() {
        assertNull(sut.getGraphicShapeAt(new Point(1, 2)));
    }

    public class GraphicLineAddedContext {

        private GraphicLine graphicShape;
        private Line line;

        @Before
        public void setUp() {
            line = new Line();
            line.setStart(new Point(11, 12));
            line.setEnd(new Point(21, 22));
            graphicShape = new GraphicLineSpy(line);
            sut.add(graphicShape);
        }

        @Test
        public void whenRemovingGraphicLine_graphicShapeListShouldBeEmpty() {
            sut.remove(graphicShape);
            assertTrue(sut.getGraphicShapeList().isEmpty());
        }

        @Test
        public void whenCallingGetGraphicShapeAtWithPointOnLine_shouldReturnGraphicShape() {
            GraphicLineSpy graphicLineSpy = (GraphicLineSpy) sut.getGraphicShapeAt(new Point(16, 17));
            assertEquals(line, graphicLineSpy.getShape());
        }

        @Test
        public void whenCallingGetGraphicShapeAtWithPointNotOnLine_shouldReturnNull() {
            assertNull(sut.getGraphicShapeAt(new Point(1, 2)));
            assertNull(sut.getGraphicShapeAt(new Point(31, 32)));
            assertNull(sut.getGraphicShapeAt(new Point(41, 42)));
        }

        @Test
        public void whenCallingRemoveWithIndex_graphicShapeListShouldBeEmpty() {
            sut.remove(0);
            assertTrue(sut.getGraphicShapeList().isEmpty());
        }

        public class TwoLinesAdded {

            private GraphicLineSpy secondLineSpy;

            @Before
            public void setUp() {
                secondLineSpy = new GraphicLineSpy(new Line());
                sut.add(secondLineSpy);
            }

            @Test
            public void whenCallingRemoveWithZero_graphicShapeListShouldOnlyContainSecond() {
                sut.remove(0);
                assertEquals(secondLineSpy, sut.getGraphicShapeList().get(0));
            }


        }
    }

}