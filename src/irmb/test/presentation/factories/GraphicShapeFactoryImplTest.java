package irmb.test.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.flowsim.presentation.factories.GraphicShapeFactoryImpl;
import irmb.flowsim.view.GraphicLine;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.view.GraphicShape;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicShapeFactoryImplTest {

    private GraphicShapeFactory shapeFactory;

    @Before
    public void setUp() throws Exception {
        shapeFactory = new GraphicShapeFactoryImpl();
    }

    @Test
    public void testMakeLine() {
        GraphicShape graphicShape = shapeFactory.makeShape(new Line());

        assertThat(graphicShape, is(instanceOf(GraphicLine.class)));
    }

    @Test
    public void testMakeRectangle() {
        GraphicShape graphicShape = shapeFactory.makeShape(new Rectangle());

        assertThat(graphicShape, is(instanceOf(GraphicRectangle.class)));
    }

    @Test
    public void testMakePolyLine() {
        GraphicShape graphicShape = shapeFactory.makeShape(new PolyLine());

        assertThat(graphicShape, is(instanceOf(GraphicPolyLine.class)));
    }

    @Test
    public void givenNull_shouldReturnNull() {
        GraphicShape graphicShape = shapeFactory.makeShape(null);

        assertNull(graphicShape);
    }

}