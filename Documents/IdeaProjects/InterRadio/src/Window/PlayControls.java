package Window;

import meme.NewMediaPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 12/3/2016.
 */
public class PlayControls {
    private JButton playButton;
    protected PlayPauseButton ppB;
    protected StopButton sB;
    private JSlider volumeSlider;
    public JPanel controlPanel;
    private JButton switchButton;
    private JButton customStationButton;
    private JButton stopButton;
    protected JTextArea textField1;
    protected StationPanelProperties[] stationPanelProperties;
    public String newline = System.getProperty("line.separator");
    protected NewMediaPlayer player = new NewMediaPlayer();
    protected int i = 0;
    protected customStationFrame csframe;
    protected JButton enter = new JButton("enter");

    public PlayControls() {
        textField1.setOpaque(false);
        textField1.setText("Now Playing: ");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.start(stationPanelProperties[i].getStation()[2]);
                textField1.setText("Now Playing: " + stationPanelProperties[i].getStationName());
                if (i == stationPanelProperties.length - 1) {
                    i = 0;
                } else {
                    i++;
                }
                ppB.setPlaying(true);
                player.setVolume(volumeSlider.getValue());
            }
        });
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                player.setVolume(volumeSlider.getValue());
            }
        });
        ppB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ppB.changeState();
                player.pause();
                player.setVolume(volumeSlider.getValue());
            }
        });
        sB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getOurMediaPlayer().stop();
                ppB.setPlaying(false);
                textField1.setText("Now Playing:");
            }
        });
        customStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csframe = new customStationFrame();
                csframe.getEnter().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enter.doClick();
                    }
                });
                   csframe.setVisible(true);
            }
        });
    }

    public JTextArea getTextField1() {
        return textField1;
    }

    public void setActiveStreams(StationPanelProperties[] s) {
        stationPanelProperties = s;
        i = 0;
    }

    public void playNow(StationPanelProperties s) {
        player.start(s.getStation()[2]);
        player.setVolume(volumeSlider.getValue());
    }

    public PlayPauseButton getppB() {
        return ppB;
    }

    public void addEnterListener(ActionListener e) {
        enter.addActionListener(e);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        ppB = new PlayPauseButton();
        if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0){
            ppB.setBig(true);
        }
        if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0){
            ppB.setBig(true);
        }
        playButton = ppB;
        sB = new StopButton();
        stopButton = sB;
    }
}
