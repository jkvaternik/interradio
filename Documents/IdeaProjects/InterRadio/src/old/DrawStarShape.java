package old;

import javax.swing.*;

/**
 * Created by Alex on 12/12/2016.
 */
public class DrawStarShape
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new StarPanel());
        f.setSize(600, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}

