package meme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 Created by jaimekvaternik on 12/8/16.
 */
public class armedButton extends JButton implements ActionListener{
    public boolean pressed = false;
    public armedButton() {
        super();
        addActionListener(this);
    }

   /* public void clickHold() {
        Dimension size = getSize();
        model.setArmed(true);
        model.setPressed(true);
        paintImmediately(new Rectangle(0,0, size.width, size.height));
    }

    public void unClick() {
        Dimension size = getSize();
        model.setPressed(false);
        model.setArmed(false);
        paintImmediately(new Rectangle(0,0, size.width, size.height));
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pressed) {
           // clickHold();
            setIcon(getPressedIcon());
            pressed = true;
        }
        else if (pressed) {
            //unClick();
            setIcon(getIcon());
            pressed = false;
        }
    }
}