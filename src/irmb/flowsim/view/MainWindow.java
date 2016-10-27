package irmb.flowsim.view;

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
        lineButton.addActionListener(e -> presenter.setObjectType("GraphicLine"));
        polyLineButton.addActionListener(e -> presenter.setObjectType("GraphicPolyLine"));
        rectangleButton.addActionListener(e -> presenter.setObjectType("GraphicRectangle"));
        circleButton.addActionListener(e -> presenter.setObjectType("Circle"));
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
        ((GraphicPanel) drawPanel).setPresenter(presenter);
    }

    public GraphicPanel getGraphicPanel() {
        return (GraphicPanel) drawPanel;
    }

    public void setGraphicPanel(GraphicPanel panel) {
        this.drawPanel = panel;
        this.panel.add(drawPanel);
    }

    private void createUIComponents() {
        drawPanel = new GraphicPanel();
        ((GraphicPanel) drawPanel).setPresenter(presenter);
    }
}
