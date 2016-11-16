package irmb.test.presentation.factories;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.test.presentation.GraphicViewPresenterTest;
import irmb.test.presentation.GraphicViewSpyForLine;
import org.junit.Test;
import org.junit.runner.RunWith;

import static irmb.test.presentation.TestUtil.assertExpectedPointEqualsActual;

/**
 * Created by Sven on 16.11.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicViewPresenterTestForMoveShape extends GraphicViewPresenterTest {

    @Test
    public void moveLineAcceptanceTest() {
        GraphicViewSpyForLine graphicViewSpyForLine = new GraphicViewSpyForLine();
        GraphicShapeFactory graphicShapeFactory = new GraphicShapeFactoryStub();
        GraphicShapeBuilderFactory graphicShapeBuilderFactory = new GraphicShapeBuilderFactoryImpl(graphicShapeFactory);
        GraphicViewPresenter sut = new GraphicViewPresenter(graphicShapeBuilderFactory);
        sut.setGraphicView(graphicViewSpyForLine);

        buildLineWith(first, second);
        Point pointOnLine = new Point(16, 17);
        sut.handleLeftClick(pointOnLine.getX(), pointOnLine.getY());
        assertExpectedPointEqualsActual(first, graphicViewSpyForLine.getFirst());
        assertExpectedPointEqualsActual(second, graphicViewSpyForLine.getSecond());


        sut.handleMouseDrag(third.getX(), third.getY());
        Point newStart = new Point(third.getX() - 5, third.getY() - 5);
        Point newEnd = new Point(third.getX() + 5, third.getY() + 5);
        assertExpectedPointEqualsActual(newStart, graphicViewSpyForLine.getFirst());
        assertExpectedPointEqualsActual(newEnd, graphicViewSpyForLine.getSecond());

    }
}
