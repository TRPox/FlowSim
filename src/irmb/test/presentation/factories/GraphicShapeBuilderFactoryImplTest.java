package irmb.test.presentation.factories;

import irmb.flowsim.presentation.builders.GraphicLineBuilder;
import irmb.flowsim.presentation.builders.GraphicPolyLineBuilder;
import irmb.flowsim.presentation.builders.GraphicRectangleBuilder;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
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
        GraphicShapeBuilder builder = factory.makeShapeBuilder("Line");
        assertThat(builder, is(instanceOf(GraphicLineBuilder.class)));
    }

    @Test
    public void testMakeRectangleBuilder() {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        GraphicShapeBuilder builder = factory.makeShapeBuilder("Rectangle");
        assertThat(builder, is(instanceOf(GraphicRectangleBuilder.class)));
    }

    @Test
    public void testMakePolyLineBuilder() {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        GraphicShapeBuilder builder = factory.makeShapeBuilder("PolyLine");
        assertThat(builder, is(instanceOf(GraphicPolyLineBuilder.class)));
    }


}