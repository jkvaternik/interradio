package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by Alex on 12/18/2016.
 */
public class PlayPauseButton extends JButton {
    public boolean playing = false;
    protected boolean big = true;
    protected Dimension size;

    public PlayPauseButton() {
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
        if (getModel().isArmed()) {
            if (playing) {
                g.fill(createPause(getWidth()/2, getHeight()/2, getHeight()/2, getWidth()/2));
            }
            else {
                g.fill(createTriangle(getWidth()/2, getHeight()/2, getHeight()/2, getWidth()/2));
            }
        }
        else if (playing) {
            g.fill(createPause(getWidth()/2, getHeight()/2, getHeight()/2, getWidth()/2));
        }
        else {
            g.fill(createTriangle(getWidth()/2, getHeight()/2, getHeight()/2, getWidth()/2));
        }
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

    public static Shape createTriangle(double centerX, double centerY, double height, double width) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2, centerY + height/2);
        path.lineTo(centerX + width/2, centerY);
        path.lineTo(centerX - width/2, centerY - height/2);
        path.lineTo(centerX - width/2, centerY + height/2);
        path.closePath();
        return path;
    }

    public static Shape createPause(double centerX, double centerY, double height, double width) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2, centerY - height/2);
        path.lineTo(centerX - width/2, centerY + height/2);
        path.lineTo(centerX - width/2 + width/4, centerY + height/2);
        path.lineTo(centerX - width/2 + width/4, centerY - height/2);
        path.lineTo(centerX - width/2, centerY - height/2);
        path.moveTo(centerX + width/2, centerY - height/2);
        path.lineTo(centerX + width/2, centerY + height/2);
        path.lineTo(centerX + width/2 - width/4, centerY + height/2);
        path.lineTo(centerX + width/2 - width/4, centerY - height/2);
        path.lineTo(centerX + width/2, centerY - height/2);
        path.closePath();
        return path;
    }

    public void changeState() {
        if (playing) {
            playing = false;
        }
        else {
            playing = true;
        }
    }

    protected void paintBorder(Graphics gr) {
        gr.setColor(getForeground());
        Graphics2D g = (Graphics2D) gr;
        super.paintBorder(g);
    }
}
