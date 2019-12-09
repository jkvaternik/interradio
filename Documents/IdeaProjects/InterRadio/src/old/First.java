package old;

import javax.swing.*;
import java.io.File;

/**
 * Created by Alex on 11/27/2016.
 */
public class First {
    private static JFileChooser ourFileSelector = new JFileChooser();

    public static void main(String[] args) {
        String vlcPath = "", mediaPath = "";
        File ourFile;

        ourFileSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ourFileSelector.showSaveDialog(null);

        ourFile = ourFileSelector.getSelectedFile();
        vlcPath = ourFile.getAbsolutePath();

        ourFileSelector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        ourFileSelector.showSaveDialog(null);

        ourFile = ourFileSelector.getSelectedFile();
        mediaPath = ourFile.getAbsolutePath();

        new MediaPlayer().start(mediaPath);
    }
}
