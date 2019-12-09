package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by Alex on 12/12/2016.
 */
public class PlayButton extends JButton {
    public boolean turnedOn = false;
    protected boolean big = false;
    protected Dimension size;

    public PlayButton() {
        super();

        size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);

        if (big) {
            setPreferredSize(size);
        }
        else {
            setPreferredSize(new Dimension(18, 18));
        }

        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            // You might want to make the highlight color
            // a property of the RoundButton class.
            g.setPaint(Color.GREEN);
            g.fill(createTriangle(getWidth()/2, getHeight()/2, getWidth(), getHeight()));
        }
        else if (turnedOn) {
            g.setPaint(Color.GREEN);
            g.fill(createTriangle(getWidth()/2, getHeight()/2, getWidth(), getHeight()));
        }
        else {
            g.setPaint(Color.WHITE);
            g.fill(createTriangle(getWidth()/2, getHeight()/2, getWidth(), getHeight()));
        }

        // This call will paint the label and the focus rectangle.
        //super.paintComponent(g);
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

    private static Shape createTriangle(double centerX, double centerY, double height, double width)
    {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2, centerY + height/2);
        path.lineTo(centerX + width/2, centerY);
        path.lineTo(centerX - width/2, centerY - height/2);
        path.lineTo(centerX - width/2, centerY + height/2);
        path.closePath();
        return path;
    }

    public void changeState() {
        if (turnedOn) {
            turnedOn = false;
        }
        else {
            turnedOn = true;
        }
    }

    protected void paintBorder(Graphics gr) {
        gr.setColor(getForeground());
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.draw(createTriangle(getWidth()/2, getHeight()/2, getWidth(), getHeight()));
    }

}
