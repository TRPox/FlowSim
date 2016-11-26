package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicShapeRepository;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.test.presentation.GraphicViewPresenterTest;
import irmb.test.presentation.GraphicViewSpyForLine;
import irmb.test.presentation.factories.GraphicShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;

/**
 * Created by Sven on 16.11.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicViewPresenterTestForShapeMoving extends GraphicViewPresenterTest {

    private Point newStart;
    private Point newEnd;


    @Before
    public void setUp() {
        GraphicShapeFactory graphicShapeFactory = new GraphicShapeFactoryStub();
        GraphicShapeBuilderFactory graphicShapeBuilderFactory = new GraphicShapeBuilderFactoryImpl(graphicShapeFactory);
        GraphicShapeRepository repository = new GraphicShapeRepository();
        repository.setToleranceRadius(0);
        sut = new GraphicViewPresenter(graphicShapeBuilderFactory);
        sut.setRepository(repository);
    }

    public class MoveLineContext {

        private GraphicViewSpyForLine graphicViewSpyForLine;
        private final Point pointOnLine = new Point(16, 17);

        @Before
        public void setUp() {
            graphicViewSpyForLine = new GraphicViewSpyForLine();
            sut.setGraphicView(graphicViewSpyForLine);
        }

        @Test
        public void moveLineAcceptanceTest() {
            buildLineWith(first, second);

            sut.handleLeftClick(pointOnLine.getX(), pointOnLine.getY());
            assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());

            sut.handleMouseDrag(third.getX(), third.getY());
            newStart = new Point(third.getX() - 5, third.getY() - 5);
            newEnd = new Point(third.getX() + 5, third.getY() + 5);
            assertExpectedPointEqualsActual(newStart, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(newEnd, graphicViewSpyForLine.getSecond());

            sut.handleMouseDrag(fourth.getX(), fourth.getY());
            newStart = new Point(fourth.getX() - 5, fourth.getY() - 5);
            newEnd = new Point(fourth.getX() + 5, fourth.getY() + 5);
            assertExpectedPointEqualsActual(newStart, graphicViewSpyForLine.getFirst());
            assertExpectedPointEqualsActual(newEnd, graphicViewSpyForLine.getSecond());
        }

        @Test
        public void whenDraggingMouse_shouldDoNothing() {
            sut.handleMouseDrag(first.getX(), first.getY());
        }


        public class OneLineAdded {
            @Before
            public void setUp() {
                buildLineWith(first, second);
            }

            @Test
            public void whenDraggingMouseFromPointNotOnShape_shouldDoNothing() {
                sut.handleLeftClick(third.getX(), third.getY());
                sut.handleMouseDrag(fourth.getX(), fourth.getY());

                assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
                assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());
            }

            public class LineMovedContext {
                @Before
                public void setUp() {
                    sut.handleLeftClick(pointOnLine.getX(), pointOnLine.getY());
                    sut.handleMouseDrag(third.getX(), third.getY());
                    newStart = new Point(third.getX() - 5, third.getY() - 5);
                    newEnd = new Point(third.getX() + 5, third.getY() + 5);
                }

                @Test
                public void whenReleasingMouseThenDraggingFromPointNotOnLine_shouldNotMoveLine() {
                    sut.handleMouseRelease();
                    sut.handleLeftClick(fourth.getX(), fourth.getY());
                    sut.handleMouseDrag(first.getX(), first.getY());

                    assertExpectedPointEqualsActual(newStart, graphicViewSpyForLine.getFirst());
                    assertExpectedPointEqualsActual(newEnd, graphicViewSpyForLine.getSecond());
                }
            }
        }
    }

    public class MoveRectangleContext {

        private Point pointOnShape = new Point(11, 17);

        @Test
        public void moveRectangleAcceptanceTest() {
            GraphicViewSpyForRectangle graphicViewSpyForRectangle = new GraphicViewSpyForRectangle();
            sut.setGraphicView(graphicViewSpyForRectangle);

            sut.setObjectType("Rectangle");
            sut.handleLeftClick(first.getX(), first.getY());
            sut.handleLeftClick(second.getX(), second.getY());
            int width = Math.abs(second.getX() - first.getX());

            sut.handleLeftClick(pointOnShape.getX(), pointOnShape.getY());


            sut.handleMouseDrag(third.getX(), third.getY());
            newStart = new Point(third.getX(), third.getY() - 5);
            newEnd = new Point(third.getX() + width, third.getY() + 5);
            assertExpectedPointEqualsActual(newStart, graphicViewSpyForRectangle.getFirst());
            assertExpectedPointEqualsActual(newEnd, graphicViewSpyForRectangle.getSecond());

            sut.handleMouseDrag(fourth.getX(), fourth.getY());
            newStart = new Point(fourth.getX(), fourth.getY() - 5);
            newEnd = new Point(fourth.getX() + width, fourth.getY() + 5);
            assertExpectedPointEqualsActual(newStart, graphicViewSpyForRectangle.getFirst());
            assertExpectedPointEqualsActual(newEnd, graphicViewSpyForRectangle.getSecond());
        }
    }

}
