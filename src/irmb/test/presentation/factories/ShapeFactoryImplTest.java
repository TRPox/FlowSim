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
import static org.junit.Assert.*;

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
        Shape shape = shapeFactory.makeShape("Line");

        assertThat(shape, is(instanceOf(Line.class)));
    }

    @Test
    public void testMakeRectangle() {
        Shape shape = shapeFactory.makeShape("Rectangle");

        assertThat(shape, is(instanceOf(Rectangle.class)));
    }

    @Test
    public void testMakePolyLine() {
        Shape shape = shapeFactory.makeShape("PolyLine");

        assertThat(shape, is(instanceOf(PolyLine.class)));
    }

    @Test
    public void givenInvalidString_shouldReturnNull() {
        Shape shape = shapeFactory.makeShape("Invalid");

        assertNull(shape);
    }

}