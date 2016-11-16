package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;
import irmb.test.presentation.factories.GraphicShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 19.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicViewPresenterTestForShapeBuilding extends GraphicViewPresenterTest {

    private GraphicViewSpy graphicViewSpy;

    @Before
    public void setUp() throws Exception {
        GraphicShapeFactoryStub graphicShapeFactoryStub = new GraphicShapeFactoryStub();
        GraphicShapeBuilderFactory graphicShapeBuilderFactory = new GraphicShapeBuilderFactoryImpl(graphicShapeFactoryStub);
        sut = new GraphicViewPresenter(graphicShapeBuilderFactory);
        graphicViewSpy = new GraphicViewSpy();
        sut.setGraphicView(graphicViewSpy);
    }

    @Test
    public void whenLeftClickingTwiceBeforeSettingObjectType_spyShouldNotReceiveData() {
        transmitTwoPointsToPresenter(first, second);

        assertFalse(graphicViewSpy.hasReceivedLine());
        assertFalse(graphicViewSpy.hasReceivedRectangle());
        assertFalse(graphicViewSpy.hasReceivedPolyLine());
    }

    public class BuildLineContext {

        private GraphicViewSpyForLine graphicViewSpy;

        @Before
        public void setUp() {
            graphicViewSpy = new GraphicViewSpyForLine();
            sut.setGraphicView(graphicViewSpy);
            sut.setObjectType("Line");
        }

        @Test
        public void buildLineAcceptanceTest() {
            transmitTwoPointsToPresenter(first, second);

            assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
        }

        @Test
        public void whenLeftClickingOnce_spyShouldNotReceiveLine() {
            sut.handleLeftClick(0, 0);
            assertFalse(GraphicViewPresenterTestForShapeBuilding.this.graphicViewSpy.hasReceivedLine());
        }

        @Test
        public void whenLeftClickingTwice_spyShouldReceiveLine() {
            transmitTwoPointsToPresenter(first, second);
            assertTrue(graphicViewSpy.hasReceivedLine());
        }

        @Test
        public void whenLeftClickingTwice_spyShouldReceiveLineWithCorrectCoordinates() {
            transmitTwoPointsToPresenter(first, second);

            assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
        }

        @Test
        public void whenLeftClickingThreeTimes_spyShouldNotReceiveLine() {
            transmitTwoPointsToPresenter(first, second);

            assertEquals(1, graphicViewSpy.getTimesReceiveLineCalled());
        }

        public class LivePaintingLineContext {
            @Test
            public void livePaintingLineAcceptanceTest() {
                sut.handleLeftClick(first.getX(), first.getY());
                sut.handleMouseMove(second.getX(), second.getY());
                assertTrue(graphicViewSpy.hasReceivedLine());
                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());

                sut.handleMouseMove(third.getX(), third.getY());
                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(third, graphicViewSpy.getSecond());

                sut.handleLeftClick(third.getX(), third.getY());

                sut.handleMouseMove(fourth.getX(), fourth.getY());
                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(third, graphicViewSpy.getSecond());
            }

            @Test
            public void whenMovingMouseBeforeLeftClicking_graphicSpyShouldNotReceiveLine() {
                sut.handleMouseMove(first.getX(), first.getY());
                assertFalse(graphicViewSpy.hasReceivedLine());
            }

            public class LeftClickedOnceContext {
                @Before
                public void setUp() {
                    sut.handleLeftClick(first.getX(), first.getY());
                }

                @Test
                public void whenMovingMouse_graphicSpyShouldReceiveLine() {
                    sut.handleMouseMove(second.getX(), second.getY());
                    assertTrue(graphicViewSpy.hasReceivedLine());
                }

                @Test
                public void whenMovingMouse_graphicSpyShouldReceiveLineWithCorrectCoordinates() {
                    sut.handleMouseMove(second.getX(), second.getY());

                    assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                    assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
                }

                @Test
                public void whenMovingMouseTwice_shouldAdjustLastPoint() {
                    sut.handleMouseMove(second.getX(), second.getY());
                    sut.handleMouseMove(third.getX(), third.getY());

                    assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                    assertExpectedPointEqualsActual(third, graphicViewSpy.getSecond());
                }

                public class MovedMouseOnceContext {
                    @Before
                    public void setUp() {
                        sut.handleMouseMove(second.getX(), second.getY());
                    }

                    @Test
                    public void whenMovingMouseAfterLeftClick_shouldNotAdjustLastPoint() {
                        sut.handleLeftClick(second.getX(), second.getY());
                        sut.handleMouseMove(third.getX(), third.getY());

                        assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                        assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
                    }
                }
            }
        }

    }

    public class BuildRectangleContext {

        private GraphicViewSpyForRectangle graphicViewSpy;

        @Before
        public void setUp() {
            graphicViewSpy = new GraphicViewSpyForRectangle();
            sut.setGraphicView(graphicViewSpy);
            sut.setObjectType("Rectangle");
        }

        @Test
        public void buildRectangleAcceptanceTest() {
            transmitTwoPointsToPresenter(first, second);

            assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
            assertTrue(graphicViewSpy.hasReceivedRectangle());
        }

        public class LivePaintingRectangleContext {

            @Test
            public void livePaintingRectangleAcceptanceTest() {
                sut.handleLeftClick(first.getX(), first.getY());

                sut.handleMouseMove(second.getX(), second.getY());
                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());

                sut.handleMouseMove(third.getX(), third.getY());
                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(third, graphicViewSpy.getSecond());

                sut.handleLeftClick(third.getX(), third.getY());

                sut.handleMouseMove(fourth.getX(), fourth.getY());
                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(third, graphicViewSpy.getSecond());
            }

            @Test
            public void whenMovingMouseAfterCompletingObject_shouldNotAdjustLastPoint() {
                transmitTwoPointsToPresenter(first, second);
                sut.handleMouseMove(third.getX(), third.getY());

                assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
            }
        }

    }

    public class BuildPolyLineContext {
        private List<Point> pointList;
        private GraphicViewSpyForPolyLine graphicViewSpy;

        @Before
        public void setUp() {
            graphicViewSpy = new GraphicViewSpyForPolyLine();
            sut.setGraphicView(graphicViewSpy);
            sut.setObjectType("PolyLine");
        }

        @Test
        public void buildPolyLineAcceptanceTest() {
            transmitTwoPointsToPresenter(first, second);
            assertTrue(graphicViewSpy.hasReceivedPolyLine());
            pointList = updatePointList();
            assertExpectedPointEqualsActual(first, pointList.get(0));
            assertExpectedPointEqualsActual(second, pointList.get(1));

            sut.handleLeftClick(third.getX(), third.getY());
            pointList = updatePointList();
            assertExpectedPointEqualsActual(first, pointList.get(0));
            assertExpectedPointEqualsActual(second, pointList.get(1));
            assertExpectedPointEqualsActual(third, pointList.get(2));
        }

        private List<Point> updatePointList() {
            return graphicViewSpy.getReceivedPolyLinePointList();
        }

        @Test
        public void whenLeftClickingTwice_spyShouldReceivePolyLine() {
            transmitTwoPointsToPresenter(first, second);

            assertTrue(graphicViewSpy.hasReceivedPolyLine());
        }

        @Test
        public void whenLeftClickingTwice_spyShouldReceivePolyLineWithCorrectPoints() {
            transmitTwoPointsToPresenter(first, second);

            pointList = updatePointList();
            assertExpectedPointEqualsActual(first, pointList.get(0));
            assertExpectedPointEqualsActual(second, pointList.get(1));
        }


        public class LeftClickedTwiceContext {
            @Before
            public void setUp() {
                transmitTwoPointsToPresenter(first, second);
            }

            @Test
            public void whenLeftClickingThreeTimes_spyShouldReceivePolyLineWithCorrectCoordinates() {
                sut.handleLeftClick(third.getX(), third.getY());

                pointList = updatePointList();
                assertExpectedPointEqualsActual(first, pointList.get(0));
                assertExpectedPointEqualsActual(second, pointList.get(1));
                assertExpectedPointEqualsActual(third, pointList.get(2));
            }
        }

        public class LivePaintingPolyLineContext {
            @Test
            public void livePaintingPolyLineAcceptance() {
                List<Point> pointList;

                sut.handleLeftClick(first.getX(), first.getY());

                sut.handleMouseMove(second.getX(), second.getY());
                pointList = updatePointList();
                assertTrue(graphicViewSpy.hasReceivedPolyLine());
                assertEquals(2, pointList.size());
                assertExpectedPointEqualsActual(first, pointList.get(0));
                assertExpectedPointEqualsActual(second, pointList.get(1));

                sut.handleMouseMove(third.getX(), third.getY());
                pointList = updatePointList();
                assertTrue(graphicViewSpy.hasReceivedPolyLine());
                assertEquals(2, pointList.size());
                assertExpectedPointEqualsActual(first, pointList.get(0));
                assertExpectedPointEqualsActual(third, pointList.get(1));

                sut.handleLeftClick(third.getX(), third.getY());

                sut.handleMouseMove(fourth.getX(), fourth.getY());
                pointList = updatePointList();
                assertEquals(3, pointList.size());
                assertExpectedPointEqualsActual(first, pointList.get(0));
                assertExpectedPointEqualsActual(third, pointList.get(1));
                assertExpectedPointEqualsActual(fourth, pointList.get(2));
            }
        }

    }

    public class CancelPaintingContext {

        private GraphicViewSpy graphicViewSpy;

        public class CancelLineContext {

            @Before
            public void setUp() {
                graphicViewSpy = new GraphicViewSpy();
                sut.setGraphicView(graphicViewSpy);
                sut.setObjectType("Line");
                sut.handleLeftClick(first.getX(), first.getY());

            }

            @Test
            public void whenRightClickingThenLeftClicking_shouldNotTransmitShapeToView() {
                sut.handleRightClick(first.getX(), first.getY());
                sut.handleLeftClick(second.getX(), second.getY());

                assertFalse(graphicViewSpy.hasReceivedShape());
            }

            @Test
            public void whenMovingMouseThenRightClicking_shouldRemoveLine() {
                sut.handleMouseMove(second.getX(), second.getY());
                sut.handleRightClick(second.getX(), second.getY());

                assertTrue(graphicViewSpy.wasLineRemoved());
            }

            @Test
            public void whenLeftClickingThenRightClicking_shouldNotRemoveLine() {
                sut.handleLeftClick(second.getX(), second.getY());
                sut.handleRightClick(second.getX(), second.getY());

                assertFalse(graphicViewSpy.wasLineRemoved());
            }
        }

        public class CancelPolyLineContext {

            private GraphicViewSpyForPolyLine graphicViewSpy;

            @Before
            public void setUp() {
                graphicViewSpy = new GraphicViewSpyForPolyLine();
                sut.setGraphicView(graphicViewSpy);
                sut.setObjectType("PolyLine");
                sut.handleLeftClick(first.getX(), first.getY());
                sut.handleMouseMove(second.getX(), second.getY());
                sut.handleLeftClick(second.getX(), second.getY());
            }

            @Test
            public void whenMovingMouseThenRightClicking_shouldRemoveLastPoint() {
                sut.handleMouseMove(third.getX(), third.getY());
                sut.handleRightClick(third.getX(), third.getY());

                List<Point> pointList = graphicViewSpy.getReceivedPolyLinePointList();
                int receivedPoints = pointList.size();
                assertEquals(2, receivedPoints);
                assertExpectedPointEqualsActual(first, pointList.get(0));
                assertExpectedPointEqualsActual(second, pointList.get(1));
            }
        }
    }

    public class ChangeObjectTypeContext {

        @Before
        public void setUp() {
            sut.setObjectType("Line");
        }

        @Test
        public void whenLeftClickingThenChangingObjectTypeThenLeftClicking_shouldNotTransmitShape() {
            sut.handleLeftClick(first.getX(), first.getY());
            sut.setObjectType("Rectangle");
            sut.handleLeftClick(second.getX(), second.getY());

            assertFalse(graphicViewSpy.hasReceivedShape());
        }
    }

}