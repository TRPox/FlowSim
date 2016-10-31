package irmb;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.GraphicShapeFactoryImpl;
import irmb.flowsim.view.MainWindow;

/**
 * Created by Sven on 20.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        MainWindow view = new MainWindow();
        view.setSize(800, 600);
        view.setVisible(true);
        GraphicViewPresenter presenter = new GraphicViewPresenter(new GraphicShapeBuilderFactoryImpl(new GraphicShapeFactoryImpl()));
        view.setPresenter(presenter);
        presenter.setGraphicView(view.getGraphicPanel());
    }
}
