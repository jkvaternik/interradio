package old;

import meme.NewMediaPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Alex on 11/27/2016.
 */
public class Test1 {
    private JButton playButton;
    private JPanel panel1;
    private JSlider slider1;
    private JPanel mediaPanel;
    private JButton startButton;
    private JButton switchButton;
    private JPanel controlPanel;
    private static NewMediaPlayer mediaPlayer = new NewMediaPlayer();
    private String[] streams = {"http://208.80.54.246:80/KHIPFMAAC", "http://16693.live.streamtheworld.com:80/KCDUFMAAC", "https://www.youtube.com/watch?v=JP42y28GTqc"};
    private int s = 0;

    public Test1() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playButton.getText() == "Play") {
                    playButton.setText("Pause");
                    mediaPlayer.play();
                } else if (playButton.getText() == "Pause") {
                    playButton.setText("Play");
                    mediaPlayer.pause();
                }
            }
        });
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mediaPlayer.setVolume(slider1.getValue());
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.start(streams[s]);
              //  HeadlessMediaPlayer head = mediaPlayerFactory.newHeadlessMediaPlayer();
                //head.playMedia(streams[0], ":sout=#transcode{vcodec=none,acodec=mp3,ab=128,channels=2,samplerate=44100}:file{dst=E:/Documents/Music/Test1.mp3}");
                startButton.setEnabled(false);
            }
        });
        mediaPlayer.setVolume(slider1.getValue());
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (s == streams.length - 1) {
                    s = 0;
                } else {
                    s++;
                }
                mediaPlayer.start(streams[s]);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Test1");
        frame.setContentPane(new Test1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
