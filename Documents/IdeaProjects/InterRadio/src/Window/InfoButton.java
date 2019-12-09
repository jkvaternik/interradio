package Window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alex on 12/12/2016.
 */
public class InfoButton extends JButton {
    protected boolean turnedOn = false;
    protected boolean big = false;
    protected Dimension size;

    public InfoButton() {
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
            //
        }

        if (big) {
            g.setPaint(Color.GRAY);
            g.fillOval(0, 0, getWidth(), getHeight());

            g.setPaint(Color.WHITE);
            g.fillOval(3, 3, getWidth() - 6, getHeight() - 6);

            g.setPaint(Color.GRAY);
            g.fillOval(getWidth() / 2 - 4, 6, 8, 8);

            g.fillRect(getWidth() / 2 - 4, 16, 8, 12);
        }
        else {
            g.setPaint(Color.GRAY);
            g.fillOval(0, 0, getWidth(), getHeight());

            g.setPaint(Color.WHITE);
            g.fillOval(1, 1, getWidth() - 2, getHeight() - 2);

            g.setPaint(Color.GRAY);
            g.fillOval(getWidth() / 2 - 2, 2, 4, 4);

            g.fillRect(getWidth() / 2 - 2, 7, 4, 8);
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
        //g.drawOval(0, 0, getWidth(), getHeight());
    }
}
