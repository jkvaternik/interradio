package old;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by Alex on 12/12/2016.
 */
public class StarPanel extends JPanel {
    public StarPanel() {
        super();
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.draw(createDefaultStar(getWidth()/2/2.63, getWidth()/2, getHeight()/2));

        g.setPaint(Color.YELLOW);
        g.fill(createDefaultStar(getWidth()/2/2.63 - 1, getWidth()/2, getHeight()/2));
/*
        g.setPaint(new RadialGradientPaint(
                new Point(400, 200), 60, new float[] { 0, 1 },
                new Color[] { Color.RED, Color.YELLOW }));
        g.fill(createStar(400, 200, 20, 60, 8, 0));

        g.setPaint(new RadialGradientPaint(
                new Point(200, 400), 50, new float[] { 0, 0.3f, 1 },
                new Color[] { Color.RED, Color.YELLOW, Color.ORANGE }));
        g.fill(createStar(200, 400, 40, 50, 20, 0));*/

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
}
