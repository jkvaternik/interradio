package meme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 Created by jaimekvaternik on 12/3/16.
 */
public class GenreMenu{
    public JButton generalButton;
    public JButton newsButton;
    public JButton musicButton;
    public JButton sportsButton;
    protected JPanel GenrePanel;
    private JButton favoritesButton;

    public GenreMenu() {
        GenrePanel.setBackground(new Color(0, 0, 0, 0));
        GenrePanel.setOpaque(false);
    }

    public JPanel getgP() {
        return GenrePanel;
    }

    public void addActionListenerD(ActionListener e) {
        generalButton.addActionListener(e);
        sportsButton.addActionListener(e);
        newsButton.addActionListener(e);
        musicButton.addActionListener(e);
        favoritesButton.addActionListener(e);
    }
}
