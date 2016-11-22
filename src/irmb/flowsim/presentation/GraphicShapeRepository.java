package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.view.graphics.GraphicLine;
import irmb.flowsim.view.graphics.GraphicShape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 21.11.2016.
 */
public class GraphicShapeRepository {

    private List<GraphicShape> graphicShapeList = new ArrayList<>();

    public void add(GraphicShape graphicShape) {
        graphicShapeList.add(graphicShape);
    }

    public void remove(GraphicShape graphicShape) {
        graphicShapeList.remove(graphicShape);
    }

    public List<GraphicShape> getGraphicShapeList() {
        return new ArrayList<>(graphicShapeList);
    }
}
