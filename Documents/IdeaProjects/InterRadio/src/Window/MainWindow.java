package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Alex on 12/4/2016.
 */
public class MainWindow extends JFrame {
    protected int oldW;
    protected int oldH;
    public MainWindow() {
        super();

        setTitle("InterRadio - 1.0");

        ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(new ImageIcon(System.getProperty("user.dir") + "/resources/16x16.png").getImage());
        imageList.add(new ImageIcon(System.getProperty("user.dir") + "/resources/32x32.png").getImage());
        imageList.add(new ImageIcon(System.getProperty("user.dir") + "/resources/64x64.png").getImage());
        imageList.add(new ImageIcon(System.getProperty("user.dir") + "/resources/128x128.png").getImage());
        imageList.add(new ImageIcon(System.getProperty("user.dir") + "/resources/white-and-black-globe-md.png").getImage());
        setIconImages(imageList);

        System.out.println("Icons Loaded" + System.nanoTime());

        setResizable(true);

        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        /*JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;*/

        //Create the menu bar.
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Stations");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("View Current Stations", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Current Stations");
                stations currentS = new stations();
                JButton okButton = new JButton("OK");
                currentS.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                frame.add(currentS);
                frame.setSize(1050, 290);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

        setJMenuBar(menuBar);

        MainPanel mainPanel = new MainPanel();
        setContentPane(mainPanel.containingPanel);


        oldW = mainPanel.getMap().getWidth();
        oldH = mainPanel.getMap().getHeight();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension size = mainPanel.getMap().getSize();
                if (Math.abs(size.width - oldW) > Math.abs(size.height - oldH)) {
                    size.height = size.width/mainPanel.getMap().prop;
                }
                else {
                    size.width = size.height * mainPanel.getMap().prop;
                }
                mainPanel.getMap().setPreferredSize(size);
                mainPanel.getMap().setSize(size);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
           // UIManager.setLookAndFeel(
               //     UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        MainWindow main = new MainWindow();
        main.setVisible(true);
    }
}
