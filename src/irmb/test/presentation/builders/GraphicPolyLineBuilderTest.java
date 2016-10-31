package irmb.test.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicPolyLineBuilder;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.flowsim.presentation.factories.GraphicShapeFactoryImpl;
import irmb.test.model.geometry.GraphicPolyLineSpy;
import irmb.test.presentation.factories.GraphicShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 20.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicPolyLineBuilderTest {
    private GraphicShapeBuilder builder;
    private final Point first = new Point(5, 3);

    @Before
    public void setUp() {
        GraphicShapeFactory factory = new GraphicShapeFactoryStub();
        builder = new GraphicPolyLineBuilder(factory);
    }

    @Test
    public void whenAddingOnePoint_shouldHaveCorrectPoint() {
        builder.addPoint(first);

        GraphicPolyLineSpy polyLine = (GraphicPolyLineSpy) builder.getShape();
        assertEquals(first, polyLine.getPointList().get(0));
    }

    public class OnePointAddedContext {

        private final Point second = new Point(10, 5);

        @Before
        public void setUp() {
            builder.addPoint(first);
        }

        @Test
        public void whenSettingLastPoint_shouldAdjustFirstPoint() {
            builder.setLastPoint(second);

            GraphicPolyLineSpy polyLine = (GraphicPolyLineSpy) builder.getShape();
            List<Point> pointList = polyLine.getPointList();
            assertEquals(1, pointList.size());
            assertEquals(second, pointList.get(0));
        }

        public class TwoPointsAddedContext {
            @Before
            public void setUp() {
                builder.addPoint(second);
            }

            @Test
            public void whenSettingLastPoint_shouldAdjustSecond() {
                Point third = new Point(10, 11);

                builder.setLastPoint(third);

                GraphicPolyLineSpy polyLine = (GraphicPolyLineSpy) builder.getShape();
                List<Point> pointList = polyLine.getPointList();
                assertEquals(2, pointList.size());
                assertEquals(first, pointList.get(0));
                assertEquals(third, pointList.get(1));
            }
        }
    }

}