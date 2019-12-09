package old;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexr on 11/28/2016.
 */
public class myCanvas extends JFrame{
    protected Canvas c;

    public myCanvas() {
        super();
        setSize(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = new Canvas();
        c.setBackground(Color.BLACK);
        add(c);
        pack();
        setVisible(false);
    }

    public Canvas getC() {
        return c;
    }
}
