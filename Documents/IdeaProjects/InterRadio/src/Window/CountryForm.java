package Window;

import meme.xlxs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alex on 12/12/2016.
 */
public class CountryForm {
    private JPanel p1;
    private JPanel p2;
    private JScrollBar scrollBar1;
    private JPanel tagHolder;
    protected StationPanel s;
    protected ArrayList<StationPanelProperties> stationPanelProperties;
    protected int a = 0;
    protected static JFrame frame;
    protected int sliderValue = 0;
    protected ArrayList<TagPanel> tags;
    protected JLabel emptyTag;
    protected String[][] StreamsURL = null;
    protected JButton update = new JButton();
    protected JButton play = new JButton("play");
    protected JButton star = new JButton("star");
    

    public CountryForm() {
        xlxs streamURLS = null;
        try {
            streamURLS = new xlxs();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StreamsURL = streamURLS.getStreams();

        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        p1.setBorder(raisedbevel);

        scrollBar1.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.println(e.getValue());
                if (e.getValue()/10 != sliderValue/10) {
                    s.setProperties(stationPanelProperties.get(e.getValue()/10));
                    a = e.getValue()/10;
                }
                sliderValue = e.getValue();
            }
        });
        s.getUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.doClick();
            }
        });
        s.getFaved().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                star.doClick();
            }
        });
    }

    public void addAdjustmentListenerD(AdjustmentListener e) {
        scrollBar1.addAdjustmentListener(e);
    }

    public void addUpdateListener(ActionListener e) {
        update.addActionListener(e);
        play.addActionListener(e);
        star.addActionListener(e);
    }

    public StationPanel getS() {
        return s;
    }

    public void updateStations() {
        stationPanelProperties = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).country) {
                countries.add(tags.get(i).tagText.getText());
            }
        }
        ArrayList<String> genres = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            if (!tags.get(i).country) {
                genres.add(tags.get(i).tagText.getText());
            }
        }
        if (!(countries.size() == 0)) {
            for (int i = 0; i < StreamsURL.length; i++) {
                for (int j = 0; j < countries.size(); j++) {
                    if (StreamsURL[i][0].contains(countries.get(j))) {
                        stationPanelProperties.add(new StationPanelProperties(StreamsURL[i]));
                    }
                }
            }
        }
        else {
            for (int i = 0; i < StreamsURL.length; i++) {
                stationPanelProperties.add(new StationPanelProperties(StreamsURL[i]));
            }
        }
        if (!(genres.size() == 0)) {
            boolean fav = false;
            ArrayList<StationPanelProperties> stationPanelProperties2 = new ArrayList<>();
            for (int j = 0; j < genres.size(); j++) {
                if (genres.get(j).equals("Favorites")) {
                    fav = true;
                }
            }
            for (int i = 0; i < stationPanelProperties.size(); i++) {
                if (fav) {
                    if (stationPanelProperties.get(i).getStation()[5].contains("T")) {
                        stationPanelProperties2.add(stationPanelProperties.get(i));
                    }
                }
                else {
                    for (int j = 0; j < genres.size(); j++) {
                        if (stationPanelProperties.get(i).getStation()[4].contains(genres.get(j))) {
                            stationPanelProperties2.add(stationPanelProperties.get(i));
                        }
                        break;
                    }
                }
            }
            stationPanelProperties = stationPanelProperties2;
        }

        for (int i = 0; i < stationPanelProperties.size(); i++) {
            if (stationPanelProperties.get(i).getStation()[5].equals("T")) {
                stationPanelProperties.get(i).getStarButton().setFavorited(true);
            }
            if (stationPanelProperties.get(i).getStation()[5].equals("F")) {
                stationPanelProperties.get(i).getStarButton().setFavorited(false);
            }
        }
        if (stationPanelProperties.size()>0) {
            s.setProperties(stationPanelProperties.get(0));
        }
        scrollBar1.setMaximum(stationPanelProperties.size() * 10);
        update.doClick();
    }

    public void addTag(String t, boolean c) {
        boolean already = false;
        tagHolder.remove(emptyTag);
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).tagText.getText().contains(t)) {
                already = true;
            }
        }
        if (!already) {
            TagPanel a = new TagPanel(t, c);
            tags.add(a);
            tagHolder.add(a);
            a.getRemoveButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeTag(a.tagText.getText(), a.country);
                    p1.setBounds(5, 5, (int) p1.getPreferredSize().getWidth(), (int) p1.getPreferredSize().getHeight());
                }
            });
        }
        updateStations();
        p1.repaint();
        p1.revalidate();
    }

    public void removeTag(String t, boolean c) {
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).tagText.getText().contains(t) && tags.get(i).country == c) {
                tags.remove(i);
                tags.trimToSize();
                break;
            }
        }
        tagHolder.removeAll();
        if (tags.size() == 0) {
            tagHolder.add(emptyTag);
        }
        else {
            for (int i = 0; i < tags.size(); i++) {
                tagHolder.add(tags.get(i));
            }
        }
        updateStations();
        p1.repaint();
        p1.revalidate();
    }

    public JPanel getPanel() {
        return p1;
    }

    public StationPanelProperties[] getStationPanelProperties() {
        return stationPanelProperties.toArray(new StationPanelProperties[stationPanelProperties.size()]);
    }

    public void loadStreams() {
        xlxs streamURLS = null;
        try {
            streamURLS = new xlxs();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StreamsURL = streamURLS.getStreams();
        updateStations();
    }

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new CountryForm().getPanel());
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tags = new ArrayList<>();
        tagHolder = new JPanel();
        emptyTag = new JLabel("No Tags Selected");
        tagHolder.add(emptyTag);

        p2 = new JPanel();
        s = new StationPanel();
        p2.add(s);

        p2.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches > 0 && a < stationPanelProperties.size() - 1) {
                    a++;
                    s.setProperties(stationPanelProperties.get(a));
                    scrollBar1.setValue(a*10);
                }
                if (notches < 0 && a > 0) {
                    a--;
                    s.setProperties(stationPanelProperties.get(a));
                    scrollBar1.setValue(a*10);
                }
            }
        });
    }
}
