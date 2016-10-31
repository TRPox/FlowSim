package irmb.flowsim.view;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.presentation.GraphicViewPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 11.10.2016.
 */
public class GraphicPanel extends JPanel implements MouseListener, MouseMotionListener, GraphicView {
    private GraphicViewPresenter presenter;
    private final List<Point> drawList;
    private String objectType = "";

    public GraphicPanel() {
        setDoubleBuffered(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        drawList = new ArrayList<>();
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if (e.getButton() == MouseEvent.BUTTON1) {
            presenter.handleLeftClick(mouseX, mouseY);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
//            presenter.handleRightClick(mouseX, mouseY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
//        presenter.handleMouseMove(mouseX, mouseY);
    }


    public void receiveLine(GraphicLine line) {
        objectType = "GraphicLine";
        drawList.clear();
//        drawList.add(line.getStart());
//        drawList.add(line.getEnd());
        repaint();
    }


    public void receiveRectangle(GraphicRectangle rectangle) {
        objectType = "GraphicRectangle";
        drawList.clear();
//        drawList.add(rectangle.getFirst());
//        drawList.add(rectangle.getSecond());
        repaint();
    }


    public void receivePolyLine(GraphicPolyLine polyLine) {
        objectType = "GraphicPolyLine";
        drawList.clear();
//        drawList.addAll(polyLine.getPointList());
        repaint();
    }

    @Override
    public void receiveShape(GraphicShape graphicShape) {

    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (objectType.equals("GraphicLine")) {
            Point first = drawList.get(0);
            Point second = drawList.get(1);
            graphics.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
        }
    }
}
