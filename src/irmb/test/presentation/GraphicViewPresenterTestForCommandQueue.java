package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.test.presentation.factories.GraphicShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 14.11.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicViewPresenterTestForCommandQueue extends GraphicViewPresenterTest {

    private GraphicViewSpyForLine graphicViewSpyForLine;
    private final Point fifth = new Point(51, 52);
    private final Point sixth = new Point(61, 62);

    @Before
    public void setUp() {
        GraphicShapeFactory graphicShapeFactory = new GraphicShapeFactoryStub();
        GraphicShapeBuilderFactory graphicShapeBuilderFactory = new GraphicShapeBuilderFactoryImpl(graphicShapeFactory);
        sut = new GraphicViewPresenter(graphicShapeBuilderFactory);
        graphicViewSpyForLine = new GraphicViewSpyForLine();
        sut.setGraphicView(graphicViewSpyForLine);
    }

    private void performMove(Point oldStart, Point newStart) {
        sut.handleLeftClick(oldStart.getX(), oldStart.getY());
        sut.handleMouseDrag(0, 0);
        sut.handleMouseDrag(newStart.getX(), newStart.getY());
        sut.handleMouseRelease();
    }

    public class ShapeBuildingQueueContext {

        @Test
        public void shapeBuildingUndoRedoAcceptanceTest() {
            final Point seventh = new Point(71, 72);
            final Point eighth = new Point(81, 82);

            buildLineWith(first, second);
            buildLineWith(third, fourth);
            buildLineWith(fifth, sixth);

            sut.undo();
            assertTrue(graphicViewSpyForLine.wasLineRemoved());
            assertExpectedPointEqualsActual(fifth, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(sixth, graphicViewSpyForLine.getSecond());

            sut.undo();
            assertEquals(2, graphicViewSpyForLine.getTimesRemoveLineCalled());
            assertExpectedPointEqualsActual(third, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(fourth, graphicViewSpyForLine.getSecond());

            sut.undo();
            assertEquals(3, graphicViewSpyForLine.getTimesRemoveLineCalled());
            assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

            sut.undo();

            sut.redo();
            assertEquals(4, graphicViewSpyForLine.getTimesReceiveLineCalled());
            assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

            sut.redo();
            assertEquals(5, graphicViewSpyForLine.getTimesReceiveLineCalled());
            assertExpectedPointEqualsActual(third, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(fourth, graphicViewSpyForLine.getSecond());

            buildLineWith(seventh, eighth);

            sut.redo();
            assertEquals(6, graphicViewSpyForLine.getTimesReceiveLineCalled());
        }


        @Test
        public void whenCallingUndo_shouldDoNothing() {
            sut.undo();

            assertFalse(graphicViewSpyForLine.wasLineRemoved());
        }

        public class LineAddedContext {

            @Before
            public void setUp() {
                buildLineWith(first, second);
            }

            @Test
            public void whenCallingUndo_shouldRemoveLine() {
                sut.undo();

                assertTrue(graphicViewSpyForLine.wasLineRemoved());
                assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
            }

            public class CalledUndoOnceContext {
                @Before
                public void setUp() {
                    sut.undo();
                }

                @Test
                public void whenCallingUndo_shouldDoNothing() {
                    sut.undo();

                    assertEquals(1, graphicViewSpyForLine.getTimesRemoveLineCalled());
                }

                @Test
                public void whenCallingRedo_shouldAddLineAgain() {
                    sut.redo();

                    assertEquals(2, graphicViewSpyForLine.getTimesReceiveLineCalled());
                    assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                    assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
                }

                @Test
                public void whenCallingRedoAfterAddingNewLine_shouldDoNothing() {
                    sut.setObjectType("Line");
                    transmitTwoPointsToPresenter(third, fourth);

                    sut.redo();
                    assertEquals(2, graphicViewSpyForLine.getTimesReceiveLineCalled());
                }
            }

            public class TwoLinesAddedContext {

                @Before
                public void setUp() {
                    buildLineWith(third, fourth);
                }

                @Test
                public void whenCallingUndoTwice_shouldRemoveBothLinesInReverseOrder() {
                    sut.undo();

                    assertTrue(graphicViewSpyForLine.wasLineRemoved());
                    assertExpectedPointEqualsActual(third, graphicViewSpyForLine.getFirst());
                    assertExpectedPointEqualsActual(fourth, graphicViewSpyForLine.getSecond());

                    sut.undo();
                    assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                    assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
                }

                public class CalledUndoTwiceContext {
                    @Before
                    public void setUp() {
                        sut.undo();
                        sut.undo();
                    }

                    @Test
                    public void whenCallingRedoTwice_shouldAddBothLinesInOriginalOrder() {
                        sut.redo();
                        assertEquals(3, graphicViewSpyForLine.getTimesReceiveLineCalled());
                        assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                        assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

                        sut.redo();
                        assertEquals(4, graphicViewSpyForLine.getTimesReceiveLineCalled());
                        assertExpectedPointEqualsActual(third, graphicViewSpyForLine.getFirst());
                        assertExpectedPointEqualsActual(fourth, graphicViewSpyForLine.getSecond());
                    }
                }
            }
        }
    }

    public class CommandQueueContext {

        private final Point newStartAfterFirstMove = new Point(first.getX() + 5, first.getY() + 5);
        private final Point newEndAfterFirstMove = new Point(second.getX() + 5, second.getY() + 5);
        private final Point newStartAfterSecondMove = new Point(first.getX() + 13, first.getY() + 12);
        private final Point newEndAfterSecondMove = new Point(second.getX() + 13, second.getY() + 12);
        private final Point newStartAfterThirdMove = new Point(first.getX() + 24, first.getY() + 7);

        @Before
        public void setUp() {
            buildLineWith(first, second);
        }

        @Test
        public void commandQueueAcceptanceTest() {
            buildLineWith(third, fourth);

            sut.undo();
            assertTrue(graphicViewSpyForLine.wasLineRemoved());
            assertExpectedPointEqualsActual(third, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(fourth, graphicViewSpyForLine.getSecond());

            sut.undo();
            assertEquals(2, graphicViewSpyForLine.getTimesRemoveLineCalled());
            assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

            sut.redo();
            assertEquals(3, graphicViewSpyForLine.getTimesReceiveLineCalled());
            assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

            performMove(first, newStartAfterFirstMove);

            sut.undo();
            assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

            sut.redo();
            assertExpectedPointEqualsActual(newStartAfterFirstMove, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(newEndAfterFirstMove, graphicViewSpyForLine.getSecond());

            performMove(newStartAfterFirstMove, newStartAfterSecondMove);
            performMove(newStartAfterSecondMove, newStartAfterThirdMove);

            sut.undo();
            assertExpectedPointEqualsActual(newStartAfterSecondMove, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(newEndAfterSecondMove, graphicViewSpyForLine.getSecond());

            buildLineWith(fifth, sixth);

            sut.undo();

            buildLineWith(third, fourth);

            sut.redo();
            assertEquals(5, graphicViewSpyForLine.getTimesReceiveLineCalled());
        }


        public class MovedOnceContext {
            @Before
            public void setUp() {
                performMove(first, newStartAfterFirstMove);
            }

            @Test
            public void whenCallingUndo_shouldUndoMove() {
                sut.undo();

                assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
            }

            @Test
            public void whenCallingUndoTwice_shouldRemoveLine() {
                sut.undo();
                sut.undo();

                assertTrue(graphicViewSpyForLine.wasLineRemoved());
                assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
            }

            public class MovedTwiceContext {
                @Before
                public void setUp() {
                    performMove(newStartAfterFirstMove, newStartAfterSecondMove);
                }

                @Test
                public void whenCallingUndoTwice_shouldUndoBothMoves() {
                    sut.undo();
                    sut.undo();

                    assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                    assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
                }
            }

            public class TwoLinesAddedContext {
                @Before
                public void setUp() {
                    buildLineWith(third, fourth);
                }

                @Test
                public void whenCallingUndo_shouldRemoveSecondLine() {
                    sut.undo();

                    assertTrue(graphicViewSpyForLine.wasLineRemoved());
                    assertExpectedPointEqualsActual(third, graphicViewSpyForLine.getFirst());
                    assertExpectedPointEqualsActual(fourth, graphicViewSpyForLine.getSecond());
                }
            }
        }
    }
}


