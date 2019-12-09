package Window;

import meme.GenreMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Alex on 12/4/2016.
 */
public class MainPanel implements ActionListener, AdjustmentListener {
    public JPanel containingPanel;
    private JPanel playControlsPanel;
    private JPanel mapPanel;
    public BufferedImage image = null;
    public BufferedImage imageOriginal = null;
    protected static GenreMenu menu;
    public PlayControls playControls;
    protected MapPanel map;
    protected CountryForm countryForm;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int zoom = 0;
    protected int[][] zoomDimensions = new int[22][4];
    protected String[][] countryRGB = new String[28][2];

    public MainPanel() {
        String list = null;
        try {
            list = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/resources/cC.txt")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(list);
        int linen = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ';') {
                    countryRGB[linen][0] = line.substring(0, i);
                    countryRGB[linen][1] = line.substring(i+1);
                }
            }
            linen++;
        }
        for (int i = 0; i < zoomDimensions.length; i++) {
            int w = 50*map.prop;
            int h = 50;
            zoomDimensions[i][0] = 0 + w*i;
            zoomDimensions[i][1] = 0 + h*i;
            zoomDimensions[i][2] = map.originalImage.getWidth() - w*i;
            zoomDimensions[i][3] = map.originalImage.getHeight() - h*i;
        }
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mapPanel.requestFocusInWindow();
                Thread checkColor = new Thread() {
                    public void run() {
                        Robot r = null;
                        try

                        {
                            r = new Robot();
                        } catch (
                                AWTException e1)

                        {
                            e1.printStackTrace();
                        }

                        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
                        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
                        Color c = r.getPixelColor(x, y);
                        System.out.println(c);

                        int pixel = image.getRGB((int) (x - map.getLocationOnScreen().getX()), (int) (y - map.getLocationOnScreen().getY()));

                        printPixelARGB(pixel);
                        for (int i = 0; i< countryRGB.length; i++) {
                            if (countryRGB[i][1].equals(getRGB(pixel))) {
                                countryForm.addTag(countryRGB[i][0], true);
                                break;
                            }
                        }
                        /*if (getRGB(pixel).equals("35,32,32")) {
                            playControls.player.getOurMediaPlayer().startMedia(System.getProperty("user.dir") + "/resources/oc.mp3");
                        }*/
                        countryForm.getPanel().setBounds(5, 5, (int) countryForm.getPanel().getPreferredSize().getWidth(), (int) countryForm.getPanel().getPreferredSize().getHeight());
                    }
                };
                checkColor.start();
            }
            public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    x1 = (int) e.getPoint().getX();
                    y1 = (int) e.getPoint().getY();
            }
        });
        mapPanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int notches = e.getWheelRotation();
                if (notches < 0 && zoom < 21) {
                    zoom -= notches;
                }
                if (notches > 0 && zoom > 0) {
                    zoom -= notches;
                }
                map.oX = zoomDimensions[zoom][0];
                map.oY = zoomDimensions[zoom][1];
                map.oX2 = zoomDimensions[zoom][2];
                map.oY2 = zoomDimensions[zoom][3];
                map.repaint();
                updateImage();
            }
        });
        mapPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 38 && zoom < 21) {
                    zoom += 1;
                }
                if (e.getKeyCode() == 40 && zoom > 0) {
                    zoom -= 1;
                }
                map.oX = zoomDimensions[zoom][0];
                map.oY = zoomDimensions[zoom][1];
                map.oX2 = zoomDimensions[zoom][2];
                map.oY2 = zoomDimensions[zoom][3];
                map.repaint();
                updateImage();
            }
        });
        containingPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updateImage();
            }
        });
        mapPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                x2 = (int) e.getPoint().getX();
                y2 = (int) e.getPoint().getY();
                if (map.oX <=0) {
                    if ((x2 - x1) < 0 && !(map.oX2 == map.originalImage.getWidth())) {
                        map.oX = map.oX - (x2 - x1);
                        map.oX2 = map.oX2 - (x2 - x1);
                        x1 = x2;
                    }
                }
                else if (map.oX2 >= map.originalImage.getWidth()) {
                    if ((x2 - x1) > 0 && !(map.oX == 0)) {
                        map.oX = map.oX - (x2 - x1);
                        map.oX2 = map.oX2 - (x2 - x1);
                        x1 = x2;
                    }
                }
                else {
                    map.oX = map.oX - (x2 - x1);
                    map.oX2 = map.oX2 - (x2 - x1);
                    x1 = x2;
                }
                if (map.oY <=0) {
                    if ((y2 - y1) < 0 && !(map.oY2 == map.originalImage.getHeight())) {
                        map.oY = map.oY - (y2 - y1);
                        map.oY2 = map.oY2 - (y2 - y1);
                        y1 = y2;
                    }
                }
                else if (map.oY2 >= map.originalImage.getHeight()) {
                    if ((y2 - y1) > 0 && !(map.oY == 0)) {
                        map.oY = map.oY - (y2 - y1);
                        map.oY2 = map.oY2 - (y2 - y1);
                        y1 = y2;
                    }
                }
                else {
                    map.oY = map.oY - (y2 - y1);
                    map.oY2 = map.oY2 - (y2 - y1);
                    y1 = y2;
                }
                map.repaint();
            }
        });
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                updateImage();
            }
        });
    }

    public MapPanel getMap() {
        return map;
    }
     public void loadImage() {
        try {
        imageOriginal = ImageIO.read(new File(System.getProperty("user.dir") + "/resources/colored.png"));
        } catch (
            IOException e1)

        {
            e1.printStackTrace();
        }

        Image a = imageOriginal.getScaledInstance((int) map.getPreferredSize().getWidth(), (int) map.getPreferredSize().getHeight(), Image.SCALE_FAST);
        image = toBufferedImage(a);
    }

    public void updateImage() {
        Thread updateImage = new Thread() {
            public void run() {
                BufferedImage a = imageOriginal.getSubimage(map.oX, map.oY, map.oX2 - map.oX, map.oY2 - map.oY);
                Image b = a.getScaledInstance((int) map.getPreferredSize().getWidth(), (int) map.getPreferredSize().getHeight(), Image.SCALE_FAST);
                image = toBufferedImage(b);
            }
        };
        updateImage.start();
    }

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }

    public String getRGB(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return red + "," + green + "," + blue;
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mapPanel = new JPanel();
        try {
            map = new MapPanel();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        mapPanel = map;

        menu = new GenreMenu();
        menu.addActionListenerD(this);
        mapPanel.add(menu.getgP());
        menu.getgP().setBounds((int) map.getPreferredSize().getWidth() - (int) menu.getgP().getPreferredSize().getWidth(), 0, (int) menu.getgP().getPreferredSize().getWidth(), (int) menu.getgP().getPreferredSize().getHeight());

        countryForm = new CountryForm();
        countryForm.addAdjustmentListenerD(this);
        countryForm.addUpdateListener(this);
        mapPanel.add(countryForm.getPanel());
        countryForm.getPanel().setBounds(5, 5, (int) countryForm.getPanel().getPreferredSize().getWidth(), (int) countryForm.getPanel().getPreferredSize().getHeight());

        playControls = new PlayControls();
        playControlsPanel = playControls.controlPanel;
        playControls.addEnterListener(this);

        loadImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mapPanel.repaint();
        String b = e.getActionCommand();
        if (b.contains("enter")) {
            countryForm.loadStreams();
        }
        if (b.contains("star")) {
            countryForm.loadStreams();
        }
        if (b.contains("play")) {
            playControls.playNow(countryForm.getS().getProperties());
            playControls.getppB().setPlaying(true);
            playControls.getTextField1().setText("Now Playing: " + countryForm.getS().getProperties().getStationName());
        }
        if (b.contains("General")) {
            countryForm.addTag("General", false);
        }
        if (b.contains("Music")) {
            countryForm.addTag("Music", false);
        }
        if (b.contains("News")) {
            countryForm.addTag("News", false);
        }
        if (b.contains("Sports")) {
            countryForm.addTag("Sports", false);
        }
        if (b.contains("Favorites")) {
            countryForm.addTag("Favorites", false);
        }
        playControls.setActiveStreams(countryForm.getStationPanelProperties());
        countryForm.getPanel().setBounds(5, 5, (int) countryForm.getPanel().getPreferredSize().getWidth(), (int) countryForm.getPanel().getPreferredSize().getHeight());
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        countryForm.getPanel().setBounds(5, 5, (int) countryForm.getPanel().getPreferredSize().getWidth(), (int) countryForm.getPanel().getPreferredSize().getHeight());
        countryForm.getPanel().setBounds(5, 5, (int) countryForm.getPanel().getWidth(), (int) countryForm.getPanel().getHeight());
        countryForm.getPanel().repaint();
        countryForm.getPanel().revalidate();
    }
}
