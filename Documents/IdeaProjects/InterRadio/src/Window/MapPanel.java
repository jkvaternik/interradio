package Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 12/4/2016.
 */
public class MapPanel extends JPanel {
    private float xScaleFactor = 0.25f;
    private float yScaleFactor = 0.25f;
    public BufferedImage originalImage = ImageIO.read(new File(System.getProperty("user.dir") + "/resources/map_of_the_world_rev1 copy.png"));
    protected boolean first = true;
    public int oX = 0;
    public int oY = 0;
    public int oX2 = originalImage.getWidth();
    public int oY2 = originalImage.getHeight();
    public int prop = originalImage.getWidth()/originalImage.getHeight();

    public MapPanel() throws IOException {
        super();
        setLayout(null);
        int newW = (int)(originalImage.getWidth() * xScaleFactor);
        int newH = (int)(originalImage.getHeight() * yScaleFactor);
        //setSize(new Dimension(newW, newH));
        setPreferredSize(new Dimension(newW, newH));
        repaint();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(originalImage, 0, 0, getWidth(), getHeight(), oX, oY, oX2, oY2, null);
        //super.paintComponent(g);
    }
}



//ZZZZZOOOOOOMMMMMMM