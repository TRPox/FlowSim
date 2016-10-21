package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;

/**
 * Created by Sven on 19.10.2016.
 */
public interface GraphicView {
    void receiveLine(Line line);

    void receiveRectangle(Rectangle rectangle);

    void receivePolyLine(PolyLine polyLine);

    void receiveShape(Shape shape);
}
