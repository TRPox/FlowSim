package irmb.test.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicLineBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.test.presentation.factories.GraphicShapeFactoryStub;
import irmb.test.view.GraphicLineSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by Sven on 20.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicLineBuilderTest extends Line {
    private GraphicLineBuilder builder;
    private Point start;
    private Point end;

    @Before
    public void setUp() throws Exception {
        GraphicShapeFactory factory = new GraphicShapeFactoryStub();
        builder = new GraphicLineBuilder(factory);
        start = new Point(5, 3);
        end = new Point(7, 8);
    }

    @Test
    public void whenAddingNoPoints_isObjectFinishedShouldBeFalse() {
        assertFalse(builder.isObjectFinished());
    }

    @Test
    public void whenAddingOnePoint_lineStartShouldEqualPoint() {
        builder.addPoint(start);

        GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
        assertEquals(line.getStart(), start);
    }

    @Test
    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
        builder.addPoint(start);
        assertFalse(builder.isObjectFinished());
    }

    @Test
    public void whenSettingLastPoint_shouldDoNothing() {
        builder.setLastPoint(start);

        GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
        assertNull(line.getStart());
        assertNull(line.getEnd());
    }

    public class OnePointAddedContext {
        @Before
        public void setUp() {
            builder.addPoint(start);
        }

        @Test
        public void whenAddingSecondPoint_lineStartShouldEqualFirstLineEndShouldEqualSecond() {
            builder.addPoint(end);

            GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
            assertEquals(line.getStart(), start);
            assertEquals(line.getEnd(), end);
        }


        @Test
        public void whenAddingSecondPoint_isObjectFinishedShouldBeTrue() {
            builder.addPoint(end);
            assertTrue(builder.isObjectFinished());
        }

        @Test
        public void whenSettingLastPoint_shouldAdjustPoint() {
            builder.setLastPoint(end);

            GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
            assertEquals(end, line.getStart());
        }

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

                GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
                assertEquals(line.getStart(), start);
                assertEquals(line.getEnd(), end);
            }

            @Test
            public void whenSettingLastPoint_shouldAdjustSecondPoint() {
                builder.setLastPoint(third);

                GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
                assertEquals(start, line.getStart());
                assertEquals(third, line.getEnd());
            }

            @Test
            public void whenSettingLastPointAfterAddingPoint_shouldAdjustSecondPoint() {
                Point point = new Point(10, 11);

                builder.addPoint(third);
                builder.setLastPoint(point);

                GraphicLineSpy line = (GraphicLineSpy) builder.getShape();
                assertEquals(point, line.getEnd());
            }
        }
    }
}