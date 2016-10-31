package irmb.flowsim.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sven on 27.10.2016.
 */
public class TestingGraphicViewImpl extends GraphicViewImpl implements MouseListener {

    public TestingGraphicViewImpl() {
        setDoubleBuffered(true);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        presenter.handleLeftClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
