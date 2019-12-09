package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by Alex on 12/18/2016.
 */
public class AddNewStationButton extends JButton {
    public AddNewStationButton() {
        super();

        setPreferredSize(new Dimension(18, 18));
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
        g.setPaint(Color.GRAY);
        g.fill(crossX(getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2));
        g.fill(crossY(getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2));
    }

    public Shape crossX(double centerX, double centerY, double width, double height) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - width/2, centerY - 2);
        path.lineTo(centerX + width/2, centerY - 2);
        path.lineTo(centerX + width/2, centerY + 2);
        path.lineTo(centerX - width/2, centerY + 2);
        path.lineTo(centerX - width/2, centerY - 2);
        path.closePath();
        return path;
    }

    public Shape crossY(double centerX, double centerY, double width, double height) {
        Path2D path = new Path2D.Double();
        path.moveTo(centerX - 2, centerY - height/2);
        path.lineTo(centerX - 2, centerY + height/2);
        path.lineTo(centerX + 2, centerY + height/2);
        path.lineTo(centerX + 2, centerY - height/2);
        path.lineTo(centerX - 2, centerY - height/2);
        path.closePath();
        return path;
    }
}
