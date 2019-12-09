package old;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexr on 12/1/2016.
 */
public class Fourth {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLayeredPane mainPane = new JLayeredPane();
        //frame.setContentPane(mainPane);
        JLabel picLabel = new JLabel();
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/map_of_the_world.png");
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        Image scaleImage = icon.getImage().getScaledInstance(w/3, h/3,Image.SCALE_SMOOTH);
        ImageIcon ico = new ImageIcon(scaleImage);
        picLabel.setIcon(ico);
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        pan.add(picLabel, BorderLayout.CENTER);
        mainPane.add(pan, JLayeredPane.PALETTE_LAYER);
        pan.setBounds(0,0, ico.getIconWidth(), ico.getIconHeight());
        JPanel a = new JPanel();
        a.add(mainPane);
        frame.add(a);
       // frame.add(pan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
