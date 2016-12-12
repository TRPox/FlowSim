package irmb.flowsim.command;

import irmb.flowsim.presentation.GraphicShapeRepository;
import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.graphics.GraphicShape;

/**
 * Created by Sven on 12.12.2016.
 */
public class BuildShapeCommand implements Command {

    private final GraphicShape graphicShape;
    private GraphicView graphicView;
    private GraphicShapeRepository repository;

    public BuildShapeCommand(GraphicShape shape, GraphicView graphicView, GraphicShapeRepository repository) {
        this.graphicShape = shape;
        this.graphicView = graphicView;
        this.repository = repository;
    }

    @Override
    public void execute() {
        graphicView.receiveShape(graphicShape);
        repository.add(graphicShape);
    }

    @Override
    public void undo() {
        graphicView.removeShape(graphicShape);
        repository.remove(graphicShape);
    }

    @Override
    public void redo() {
        execute();
    }
}
