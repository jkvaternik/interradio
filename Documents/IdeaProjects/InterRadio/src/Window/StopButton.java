package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by Alex on 12/19/2016.
 */
public class StopButton extends JButton {
    public boolean playing = false;
    protected boolean big = true;
    protected Dimension size;

    public StopButton() {
        super();
        size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);

        if (big) {
            setPreferredSize(size);
        }
        else {
            setPreferredSize(new Dimension(18, 18));
        }

    }

    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
        g.fill(createSquare(getWidth()/2, getHeight()/2, getHeight()/2, getWidth()/2));
    }

    public void setBig(boolean b) {
        big = b;
        if (big) {
            setPreferredSize(size);
        }
        else {
            setPreferredSize(new Dimension(18, 18));
        }
        repaint();
    }

    public void setPlaying(boolean p) {
        playing = p;
        repaint();
        revalidate();
    }

    public static Shape createSquare(double centerX, double centerY, double height, double width) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2, centerY - height/2);
        path.lineTo(centerX + width/2, centerY - height/2);
        path.lineTo(centerX + width/2, centerY + height/2);
        path.lineTo(centerX - width/2, centerY + height/2);
        path.lineTo(centerX - width/2, centerY - height/2);
        path.closePath();
        return path;
    }

    protected void paintBorder(Graphics gr) {
        gr.setColor(getForeground());
        Graphics2D g = (Graphics2D) gr;
        super.paintBorder(g);
    }
}
