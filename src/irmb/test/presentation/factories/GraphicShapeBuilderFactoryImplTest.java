package irmb.test.presentation.factories;

import irmb.flowsim.presentation.builders.LineBuilder;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.RectangleBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicShapeBuilderFactoryImplTest {
    @Test
    public void testMakeLineBuilder() {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        ShapeBuilder builder = factory.makeShapeBuilder("GraphicLine");
        assertThat(builder, is(instanceOf(LineBuilder.class)));
    }

    @Test
    public void testMakeRectangleBuilder() {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        ShapeBuilder builder = factory.makeShapeBuilder("GraphicRectangle");
        assertThat(builder, is(instanceOf(RectangleBuilder.class)));
    }

    @Test
    public void testMakePolyLineBuilder() {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        ShapeBuilder builder = factory.makeShapeBuilder("GraphicPolyLine");
        assertThat(builder, is(instanceOf(PolyLineBuilder.class)));
    }


}