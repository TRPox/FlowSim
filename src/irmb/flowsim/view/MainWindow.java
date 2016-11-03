package irmb.flowsim.view;

import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.presentation.GraphicViewPresenter;

import javax.swing.*;

/**
 * Created by Sven on 27.08.2016.
 */
public class MainWindow extends JFrame {


    private GraphicViewPresenter presenter;
    private JPanel panel;
    private JButton lineButton;
    private JButton polyLineButton;
    private JButton rectangleButton;
    private JButton circleButton;
    private JPanel drawPanel;

    public MainWindow() {
        this.add(panel);
        panel.setDoubleBuffered(true);
        setButtonActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setButtonActions() {
        lineButton.addActionListener(e -> presenter.setObjectType("Line"));
        polyLineButton.addActionListener(e -> presenter.setObjectType("PolyLine"));
        rectangleButton.addActionListener(e -> presenter.setObjectType("Rectangle"));
        circleButton.addActionListener(e -> presenter.setObjectType("Circle"));
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
        ((GraphicViewImpl) drawPanel).setPresenter(presenter);
    }

    public GraphicView getGraphicPanel() {
        return (GraphicView) drawPanel;
    }

    private void createUIComponents() {
        drawPanel = new TestingGraphicViewImpl();
        ((GraphicViewImpl) drawPanel).setPresenter(presenter);
    }
}
