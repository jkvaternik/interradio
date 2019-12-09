package Window;

import meme.xlxs;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;


/**
 Created by jaimekvaternik on 12/18/16.
 */
public class stations extends JPanel{


   public stations(){
      super();
      xlxs streamURLS = null;
        try {
            streamURLS = new xlxs();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
      String[] columnNames = {"Country", "Radio Station", "Stream URL", "Language", "Genre"};
      Object[][] currentStreams = streamURLS.getStreams();

      JTable table = new JTable();
      table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
      table.setFillsViewportHeight(true);

      DefaultTableModel tableModel = new DefaultTableModel(currentStreams, columnNames) {

          @Override
          public boolean isCellEditable(int row, int column) {
             //all cells false
             return false;
          }
      };

      table.setModel(tableModel);

      JScrollPane scrollPane = new JScrollPane(table);
      add(scrollPane);

   }

   private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        stations newContentPane = new stations();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

   public static void main(String[] args){
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
   }
}
