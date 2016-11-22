package irmb.test.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.presentation.GraphicShapeRepository;
import irmb.flowsim.view.graphics.GraphicLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Sven on 21.11.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicShapeRepositoryTest {

    private GraphicShapeRepository sut;

    @Before
    public void setUp() throws Exception {
        sut = new GraphicShapeRepository();
    }

    @Test
    public void canCallAddWithShape() {
        sut.add(new GraphicLine(new Line()));
    }

    @Test
    public void canCallGetShapeList() {
        sut.getGraphicShapeList();
    }

    @Test
    public void whenAddingGraphicShape_graphicShapeListShouldContainGraphicShape() {
        GraphicLine graphicShape = new GraphicLine(new Line());
        sut.add(graphicShape);
        assertTrue(sut.getGraphicShapeList().contains(graphicShape));
    }

    @Test
    public void canCallRemoveWithGraphicShape() {
        sut.remove(new GraphicLine(new Line()));
    }

    public class GraphicLineAddedContext {

        private GraphicLine graphicShape;

        @Before
        public void setUp() {
            graphicShape = new GraphicLine(new Line());
            sut.add(graphicShape);
        }

        @Test
        public void whenRemovingGraphicLine_graphicShapeListShouldBeEmpty() {
            sut.remove(graphicShape);
            assertTrue(sut.getGraphicShapeList().isEmpty());
        }
    }

}