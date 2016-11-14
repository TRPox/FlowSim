package irmb.test.presentation.factories;

import irmb.flowsim.presentation.builders.GraphicLineBuilder;
import irmb.flowsim.presentation.builders.GraphicPolyLineBuilder;
import irmb.flowsim.presentation.builders.GraphicRectangleBuilder;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicGraphicShapeBuilderFactoryImplTest {
    @Test
    public void testMakeLineBuilder() {
        GraphicShapeBuilderFactory factory = new GraphicShapeBuilderFactoryImpl(new GraphicShapeFactoryStub());
        GraphicShapeBuilder builder = factory.makeShapeBuilder("Line");
        assertThat(builder, is(instanceOf(GraphicLineBuilder.class)));
    }

    @Test
    public void testMakeRectangleBuilder() {
        GraphicShapeBuilderFactory factory = new GraphicShapeBuilderFactoryImpl(new GraphicShapeFactoryStub());
        GraphicShapeBuilder builder = factory.makeShapeBuilder("Rectangle");
        assertThat(builder, is(instanceOf(GraphicRectangleBuilder.class)));
    }

    @Test
    public void testMakePolyLineBuilder() {
        GraphicShapeBuilderFactory factory = new GraphicShapeBuilderFactoryImpl(new GraphicShapeFactoryStub());
        GraphicShapeBuilder builder = factory.makeShapeBuilder("PolyLine");
        assertThat(builder, is(instanceOf(GraphicPolyLineBuilder.class)));
    }


}