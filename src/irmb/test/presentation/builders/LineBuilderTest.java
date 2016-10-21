package irmb.test.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.LineBuilder;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 20.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class LineBuilderTest extends Line {
    private LineBuilder builder;
    private Point start;
    private Point end;

    @Before
    public void setUp() throws Exception {
        ShapeFactory factory = new ShapeFactoryImpl();
        builder = new LineBuilder(factory);
        start = new Point(5, 3);
        end = new Point(7, 8);
    }

    @Test
    public void whenAddingOnePoint_lineStartShouldEqualPoint() {
        builder.addPoint(start);

        Line line = (Line) builder.getShape();
        assertEquals(line.getStart(), start);
    }

//    @Test
//    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
//        builder.addPoint(start);
//        assertFalse(builder.isObjectFinished());
//    }

//    @Test
//    public void whenSettingLastPointBeforeAddingPoint_shouldDoNothing() {
//        builder.setLastPoint(start);
//
//        Line line = (Line) builder.getShape();
//        assertNull(line.getStart());
//    }

    public class OnePointAddedContext {
        @Before
        public void setUp() {
            builder.addPoint(start);
        }

        @Test
        public void whenAddingSecondPoint_lineStartShouldEqualFirstLineEndShouldEqualSecond() {
            builder.addPoint(end);

            Line line = (Line) builder.getShape();
            assertEquals(line.getStart(), start);
            assertEquals(line.getEnd(), end);
        }


//        @Test
//        public void whenAddingSecondPoint_isObjectFinishedShouldBeTrue() {
//            builder.addPoint(end);
//            assertTrue(builder.isObjectFinished());
//        }

//        @Test
//        public void whenSettingLastPoint_shouldAdjustPoint() {
//            builder.setLastPoint(end);
//
//            Line line = (Line) builder.getShape();
//            assertEquals(end, line.getStart());
//        }

        public class TwoPointsAddedContext {
            private final Point third = new Point(2, 8);

            @Before
            public void setUp() {
                builder.addPoint(end);
            }

            @Test
            public void whenAddingThirdPoint_lineShouldBeUnchanged() {
                Point unused = new Point(0, 0);

                builder.addPoint(unused);

                Line line = (Line) builder.getShape();
                assertEquals(line.getStart(), start);
                assertEquals(line.getEnd(), end);
            }

//            @Test
//            public void whenSettingLastPoint_shouldAdjustSecondPoint() {
//
//                builder.setLastPoint(third);
//
//                Line line = (Line) builder.getShape();
//                assertEquals(third, line.getEnd());
//            }
//
//            @Test
//            public void whenSettingLastPointAfterAddingPoint_shouldAdjustSecondPoint() {
//                Point point = new Point(10, 11);
//
//                builder.addPoint(third);
//                builder.setLastPoint(point);
//
//                Line line = (Line) builder.getShape();
//                assertEquals(point, line.getEnd());
//            }
        }
    }
}