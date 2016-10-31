package irmb.test.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicRectangleBuilder;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

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
        ShapeFactory factory = new ShapeFactoryImpl();
        rectangleBuilder = new GraphicRectangleBuilder(factory);
        first = new Point(5, 3);
        second = new Point(7, 8);
    }

    @Test
    public void whenAddingOnePoint_firstShouldEqualPoint() {
        rectangleBuilder.addPoint(first);

        GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
        assertEquals(first, rectangle.getFirst());
    }

//    @Test
//    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
//        rectangleBuilder.addPoint(first);
//        assertFalse(rectangleBuilder.isObjectFinished());
//    }

//    @Test
//    public void whenSettingLastPoint_shouldDoNothing() {
//        rectangleBuilder.setLastPoint(first);
//
//        GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
//        assertNull(rectangle.getFirst());
//    }

    public class OnePointAddedContext {
        @Before
        public void setUp() {
            rectangleBuilder.addPoint(first);
        }

        @Test
        public void whenAddingSecondPoint_rectangleShouldHaveCorrectCoordinates() {
            rectangleBuilder.addPoint(second);

            GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
            assertEquals(first, rectangle.getFirst());
            assertEquals(second, rectangle.getSecond());
        }

//        @Test
//        public void whenAddingSecondPoint_isObjectFinishedShouldBeTrue() {
//            rectangleBuilder.addPoint(second);
//            assertTrue(rectangleBuilder.isObjectFinished());
//        }

//        @Test
//        public void whenSettingLastPoint_shouldAdjustFirst() {
//            rectangleBuilder.setLastPoint(second);
//
//            GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
//            assertEquals(second, rectangle.getFirst());
//        }

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

                GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
                assertEquals(first, rectangle.getFirst());
                assertEquals(second, rectangle.getSecond());
            }

//            @Test
//            public void whenSettingLastPoint_shouldAdjustSecond() {
//                rectangleBuilder.setLastPoint(third);
//
//                GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
//                assertEquals(third, rectangle.getSecond());
//            }

//            @Test
//            public void whenSettingLastPointAfterAddingPoint_shouldAdjustSecond() {
//                Point point = new Point(10, 11);
//
//                rectangleBuilder.addPoint(third);
//                rectangleBuilder.setLastPoint(point);
//
//                GraphicRectangle rectangle = (GraphicRectangle) rectangleBuilder.getShape();
//                assertEquals(point, rectangle.getSecond());
//            }
        }
    }
}