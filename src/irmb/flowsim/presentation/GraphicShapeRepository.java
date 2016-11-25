package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
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

    public void remove(int i) {
        if (graphicShapeList.size() > 0)
            graphicShapeList.remove(i);
    }

    public List<GraphicShape> getGraphicShapeList() {
        return new ArrayList<>(graphicShapeList);
    }

    public GraphicShape getGraphicShapeAt(Point point) {
        for (GraphicShape graphicShape : graphicShapeList) {
            if (graphicShape.getShape().isPointOnBoundary(point, 0))
                return graphicShape;
        }
        return null;
    }

}
