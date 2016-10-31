package irmb.test.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicRectangleBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.test.view.GraphicRectangleSpy;
import irmb.test.presentation.factories.GraphicShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Sven on 20.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicRectangleBuilderTest {
    private GraphicRectangleBuilder rectangleBuilder;
    private Point first;
    private Point second;

    @Before
    public void setUp() throws Exception {
        GraphicShapeFactory factory = new GraphicShapeFactoryStub();
        rectangleBuilder = new GraphicRectangleBuilder(factory);
        first = new Point(5, 3);
        second = new Point(7, 8);
    }

    @Test
    public void whenAddingOnePoint_firstShouldEqualPoint() {
        rectangleBuilder.addPoint(first);

        GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
        assertEquals(first, rectangle.getFirst());
    }

//    @Test
//    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
//        rectangleBuilder.addPoint(first);
//        assertFalse(rectangleBuilder.isObjectFinished());
//    }

    @Test
    public void whenSettingLastPoint_shouldDoNothing() {
        rectangleBuilder.setLastPoint(first);

        GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
        assertNull(rectangle.getFirst());
        assertNull(rectangle.getSecond());
    }

    public class OnePointAddedContext {
        @Before
        public void setUp() {
            rectangleBuilder.addPoint(first);
        }

        @Test
        public void whenAddingSecondPoint_rectangleShouldHaveCorrectCoordinates() {
            rectangleBuilder.addPoint(second);

            GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
            assertEquals(first, rectangle.getFirst());
            assertEquals(second, rectangle.getSecond());
        }

//        @Test
//        public void whenAddingSecondPoint_isObjectFinishedShouldBeTrue() {
//            rectangleBuilder.addPoint(second);
//            assertTrue(rectangleBuilder.isObjectFinished());
//        }

        @Test
        public void whenSettingLastPoint_shouldAdjustFirst() {
            rectangleBuilder.setLastPoint(second);

            GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
            assertEquals(second, rectangle.getFirst());
        }

        public class TwoPointsAddedContext {

            private final Point third = new Point(2, 8);

            @Before
            public void setUp() {
                rectangleBuilder.addPoint(second);
            }

            @Test
            public void whenAddingThirdPoint_rectangleShouldBeUnchanged() {
                Point unused = new Point(0, 0);

                rectangleBuilder.addPoint(unused);

                GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
                assertEquals(first, rectangle.getFirst());
                assertEquals(second, rectangle.getSecond());
            }

            @Test
            public void whenSettingLastPoint_shouldAdjustSecond() {
                rectangleBuilder.setLastPoint(third);

                GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
                assertEquals(first, rectangle.getFirst());
                assertEquals(third, rectangle.getSecond());
            }

            @Test
            public void whenSettingLastPointAfterAddingPoint_shouldAdjustSecond() {
                Point point = new Point(10, 11);

                rectangleBuilder.addPoint(third);
                rectangleBuilder.setLastPoint(point);

                GraphicRectangleSpy rectangle = (GraphicRectangleSpy) rectangleBuilder.getShape();
                assertEquals(point, rectangle.getSecond());
            }
        }
    }
}