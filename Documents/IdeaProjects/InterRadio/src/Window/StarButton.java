package Window;

/**
 * Created by Alex on 12/12/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by alexr on 11/14/2016.
 */
public class StarButton extends JButton {
    protected boolean favorited = false;
    protected boolean big = false;
    protected Dimension size;

    public StarButton() {
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
            g.setPaint(Color.yellow);
            g.fill(createDefaultStar(getWidth()/2/2.63 - 1, getWidth()/2, getHeight()/2));
        }
        else if (favorited) {
            g.setPaint(Color.yellow);
            g.fill(createDefaultStar(getWidth()/2/2.63 - 1, getWidth()/2, getHeight()/2));
        }
        else {
            g.setPaint(Color.WHITE);
            g.fill(createDefaultStar(getWidth()/2/2.63 - 1, getWidth()/2, getHeight()/2));
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

    private static Shape createDefaultStar(double radius, double centerX,
                                           double centerY)
    {
        return createStar(centerX, centerY, radius, radius * 2.63, 5,
                Math.toRadians(-18));
    }

    private static Shape createStar(double centerX, double centerY,
                                    double innerRadius, double outerRadius, int numRays,
                                    double startAngleRad)
    {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++)
        {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0)
            {
                relX *= outerRadius;
                relY *= outerRadius;
            }
            else
            {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0)
            {
                path.moveTo(centerX + relX, centerY + relY);
            }
            else
            {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }

    public void changeState() {
        if (favorited) {
            favorited = false;
        }
        else {
            favorited = true;
        }
    }

    public boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(boolean f) {
        favorited = f;
        repaint();
    }

    protected void paintBorder(Graphics gr) {
        gr.setColor(getForeground());
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.draw(createDefaultStar(getWidth()/2/2.63, getWidth()/2, getHeight()/2));
    }

}