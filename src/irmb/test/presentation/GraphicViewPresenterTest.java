package irmb.test.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;

/**
 * Created by Sven on 14.11.2016.
 */
public class GraphicViewPresenterTest {

    protected GraphicViewPresenter sut;

    protected final Point first = new Point(11, 12);
    protected final Point second = new Point(21, 22);
    protected final Point third = new Point(31, 32);
    protected final Point fourth = new Point(41, 42);

    protected void transmitTwoPointsToPresenter(Point first, Point second) {
        sut.handleLeftClick(first.getX(), first.getY());
        sut.handleLeftClick(second.getX(), second.getY());
    }

    protected void buildLineWith(Point first, Point second) {
        sut.setObjectType("Line");
        transmitTwoPointsToPresenter(first, second);
    }
}
