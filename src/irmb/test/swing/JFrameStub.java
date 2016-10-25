package irmb.test.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Sven on 24.10.2016.
 */
public class JFrameStub extends JFrame {

    public void setVisible(boolean visible) {
        setSize(100, 100);
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();
        paintAll(graphics2D);
    }
}
