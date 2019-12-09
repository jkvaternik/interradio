package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by Alex on 12/17/2016.
 */
public class tagRemoveButton extends JButton {
    protected Dimension size;

    public tagRemoveButton(int h) {
        super();
        size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);

        //setPreferredSize(size);
        //setPreferredSize(new Dimension(16, 16));
        setPreferredSize(new Dimension(h, h));

        setContentAreaFilled(false);
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(Color.GRAY);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setPaint(Color.WHITE);
        g.fill(x1(getWidth()/2, getHeight()/2, 10, 10, 1));
        g.fill(x2(getWidth()/2, getHeight()/2, 10, 10, 1));
    }

    private static Shape createX(double centerX, double centerY, double height, double width) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2, centerY - height/2);
        path.lineTo(centerX + width/2, centerY + height/2);
        path.moveTo(centerX + width/2, centerY - height/2);
        path.lineTo(centerX - width/2, centerY + height/2);
        path.closePath();
        return path;
    }

    private static Shape createX2(double centerX, double centerY, double height, double width, double d) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2 + d, centerY - height/2);
        path.lineTo(centerX + width/2, centerY + height/2 - d);
        path.lineTo(centerX + width/2 - d, centerY + height/2);
        path.lineTo(centerX - width/2, centerY - height/2 + d);
        path.lineTo(centerX - width/2 + d, centerY - height/2);
        path.moveTo(centerX + width/2 - d, centerY - height/2);
        path.lineTo(centerX - width/2, centerY + height/2 - d);
        path.lineTo(centerX - width/2 + d, centerY + height/2);
        path.lineTo(centerX + width/2, centerY - height/2 + d);
        path.lineTo(centerX + width/2 - d, centerY - height/2);
        path.closePath();
        return path;
    }

    private static Shape centerS(double centerX, double centerY, double height, double width, double d1) {
        double d = 1.5 * d1;
        Path2D path = new Path2D.Double();
        path.moveTo(centerX, centerY - Math.sqrt((Math.pow(d, 2))/2));
        path.lineTo(centerX + Math.sqrt((Math.pow(d, 2))/2), centerY);
        path.lineTo(centerX, centerY + Math.sqrt((Math.pow(d, 2))/2));
        path.lineTo(centerX - Math.sqrt((Math.pow(d, 2))/2), centerY);
        path.lineTo(centerX, centerY - Math.sqrt((Math.pow(d, 2))/2));
        path.closePath();
        return path;
    }

    private static Shape x1(double centerX, double centerY, double height, double width, double d1) {
        double d = 1.5 * d1;
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2 + d, centerY - height/2);
        path.lineTo(centerX + width/2, centerY + height/2 - d);
        path.lineTo(centerX + width/2 - d, centerY + height/2);
        path.lineTo(centerX - width/2, centerY - height/2 + d);
        path.lineTo(centerX - width/2 + d, centerY - height/2);
        path.closePath();
        return path;
    }

    private static Shape x2(double centerX, double centerY, double height, double width, double d1) {
        double d = 1.5 * d1;
        Path2D path = new Path2D.Double();
        path.moveTo(centerX + width/2 - d, centerY - height/2);
        path.lineTo(centerX - width/2, centerY + height/2 - d);
        path.lineTo(centerX - width/2 + d, centerY + height/2);
        path.lineTo(centerX + width/2, centerY - height/2 + d);
        path.lineTo(centerX + width/2 - d, centerY - height/2);
        path.closePath();
        return path;
    }

    public void paintBorder(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
    }
}
