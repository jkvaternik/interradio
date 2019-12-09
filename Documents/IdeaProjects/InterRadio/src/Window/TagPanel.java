package Window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alex on 12/17/2016.
 */
public class TagPanel extends JPanel {
    protected boolean country = true;
    protected boolean genre = false;
    protected JLabel tagText;
    protected tagRemoveButton removeButton;

    public TagPanel(String tag, boolean c) {
        super();
        country = c;
        if (!c) {
            genre = true;
        }
        setLayout(new FlowLayout());
        tagText = new JLabel(tag);
        add(tagText);
        removeButton = new tagRemoveButton(16);
        add(removeButton);
    }

    public tagRemoveButton getRemoveButton() {
        return removeButton;
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setPaint(Color.white);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight()  - 1, 10, 10);
    }

    public void paintBorder(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
    }
}
