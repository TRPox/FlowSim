package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 19.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicViewPresenterTest {

    private GraphicViewPresenter sut;
    private GraphicViewSpy graphicViewSpy;
    private final Point first = new Point(1, 2);
    private final Point second = new Point(3, 4);
    private final Point third = new Point(5, 6);

    @Before
    public void setUp() throws Exception {
        graphicViewSpy = new GraphicViewSpy();
        sut = new GraphicViewPresenter();
        sut.setGraphicView(graphicViewSpy);
    }

    private void transmitTwoPointsToPresenter(Point first, Point second) {
        sut.handleLeftClick(first.getX(), first.getY());
        sut.handleLeftClick(second.getX(), second.getY());
    }

    private void assertExpectedPointEqualsActual(Point expected, Point actual) {
        assertEquals(expected.getX(), actual.getX(), 0.0001);
        assertEquals(expected.getY(), actual.getY(), 0.0001);
    }

    @Test
    public void whenLeftClickingTwiceBeforeSettingObjectType_spyShouldNotReceiveData() {
        transmitTwoPointsToPresenter(first, second);

        assertFalse(graphicViewSpy.hasReceivedLine());
        assertFalse(graphicViewSpy.hasReceivedRectangle());
        assertFalse(graphicViewSpy.hasReceivedPolyLine());
    }

    public class BuildLineContext {
        @Before
        public void setUp() {
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
            assertFalse(graphicViewSpy.hasReceivedLine());
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

            assertEquals(1, graphicViewSpy.getTimesReceivedLineCalled());
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
            }


        }

    }

    public class BuildRectangleContext {
        @Test
        public void buildRectangleAcceptanceTest() {
            sut.setObjectType("Rectangle");

            transmitTwoPointsToPresenter(first, second);

            assertExpectedPointEqualsActual(first, graphicViewSpy.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpy.getSecond());
            assertTrue(graphicViewSpy.hasReceivedRectangle());
        }
    }

    public class BuildPolyLineContext {
        private List<Point> pointList;

        @Before
        public void setUp() {
            sut.setObjectType("PolyLine");
        }

        @Test
        public void buildPolyLineAcceptanceTest() {
            transmitTwoPointsToPresenter(first, second);
            assertTrue(graphicViewSpy.hasReceivedPolyLine());
            pointList = graphicViewSpy.getReceivedPolyLinePointList();
            assertExpectedPointEqualsActual(first, pointList.get(0));
            assertExpectedPointEqualsActual(second, pointList.get(1));

            sut.handleLeftClick(third.getX(), third.getY());
            pointList = graphicViewSpy.getReceivedPolyLinePointList();
            assertExpectedPointEqualsActual(first, pointList.get(0));
            assertExpectedPointEqualsActual(second, pointList.get(1));
            assertExpectedPointEqualsActual(third, pointList.get(2));
        }

        @Test
        public void whenLeftClickingTwice_spyShouldReceivePolyLine() {
            transmitTwoPointsToPresenter(first, second);

            assertTrue(graphicViewSpy.hasReceivedPolyLine());
        }

        @Test
        public void whenLeftClickingTwice_spyShouldReceivePolyLineWithCorrectPoints() {
            transmitTwoPointsToPresenter(first, second);

            pointList = graphicViewSpy.getReceivedPolyLinePointList();
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

                pointList = graphicViewSpy.getReceivedPolyLinePointList();
                assertExpectedPointEqualsActual(first, pointList.get(0));
                assertExpectedPointEqualsActual(second, pointList.get(1));
                assertExpectedPointEqualsActual(third, pointList.get(2));
            }


        }
    }

    public class CancelPaintingContext {

        public class CancelLineContext {

            @Before
            public void setUp() {
                sut.setObjectType("Line");
                sut.handleLeftClick(first.getX(), first.getY());

            }

            @Test
            public void whenRightClickingThenLeftClicking_shouldNotTransmitShapeToView() {
                sut.handleRightClick(first.getX(), first.getY());
                sut.handleLeftClick(second.getX(), second.getY());

                assertFalse(graphicViewSpy.hasReceivedShape());
            }
        }

//        public class CancelPolyLineContext {
//            @Before
//            public void setUp() {
//                sut.setObjectType("PolyLine");
//                sut.handleLeftClick(first.getX(), first.getY());
//                sut.handleLeftClick(second.getX(), second.getY());
//
//            }
//
//            @Test
//            public void whenRightClickingThenLeftClicking_shouldNotAddPoint() {
//                sut.handleRightClick(second.getX(), second.getY());
//                sut.handleLeftClick(third.getX(), third.getY());
//
//                List<Point> pointList = graphicViewSpy.getReceivedPolyLinePointList();
//                assertEquals(2, pointList.size());
//                assertExpectedPointEqualsActual(first, pointList.get(0));
//                assertExpectedPointEqualsActual(second, pointList.get(1));
//            }
//        }

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