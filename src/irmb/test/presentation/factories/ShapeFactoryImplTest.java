package irmb.test.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Sven on 20.10.2016.
 */
public class ShapeFactoryImplTest {

    private ShapeFactory shapeFactory;

    @Before
    public void setUp() throws Exception {
        shapeFactory = new ShapeFactoryImpl();
    }

    @Test
    public void testMakeLine() {
        Shape graphicShape = shapeFactory.makeShape("Line");

        assertThat(graphicShape, is(instanceOf(Line.class)));
    }

    @Test
    public void testMakeRectangle() {
        Shape graphicShape = shapeFactory.makeShape("Rectangle");

        assertThat(graphicShape, is(instanceOf(Rectangle.class)));
    }

    @Test
    public void testMakePolyLine() {
        Shape graphicShape = shapeFactory.makeShape("PolyLine");

        assertThat(graphicShape, is(instanceOf(PolyLine.class)));
    }

    @Test
    public void givenInvalidString_shouldReturnNull() {
        Shape graphicShape = shapeFactory.makeShape("Invalid");

        assertNull(graphicShape);
    }

}