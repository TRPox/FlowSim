package irmb.flowsim.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Sven on 27.10.2016.
 */
public class TestingGraphicViewImpl extends GraphicViewImpl implements MouseListener, MouseMotionListener {

    public TestingGraphicViewImpl() {
        setDoubleBuffered(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            presenter.handleLeftClick(e.getX(), e.getY());
        else if (e.getButton() == MouseEvent.BUTTON3)
            presenter.handleRightClick(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        presenter.handleMouseRelease();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        presenter.handleMouseDrag(e.getX(), e.getY());

//        System.out.println("Dragged: " + e.getX() + " " + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        presenter.handleMouseMove(e.getX(), e.getY());
    }
}
