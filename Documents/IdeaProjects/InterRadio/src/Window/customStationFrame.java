package Window;

import meme.NewMediaPlayer;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.prompt.BuddySupport;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 Created by jaimekvaternik on 12/14/16.
 */
public class customStationFrame extends JFrame{
   public JTextField insertRadioStationNameTextField;
   public JTextField insertStreamURLTextField;
   public JComboBox LangBox;
   public JTextField insertGenreTextField;
   private JButton enterButton;
   private JPanel panel1;
   private JComboBox CountryBox;
   private String[] countries;
   private String[] language;
   private NewMediaPlayer player = new NewMediaPlayer();
   private ImageIcon loadIcon = new ImageIcon(System.getProperty("user.dir") + "/resources/hourglass-icon.png");
   private ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/resources/warning.png");
   protected JButton enter = new JButton("enter");

   public customStationFrame(){
         super();

         PromptSupport prompt1 = new PromptSupport();
         PromptSupport prompt2 = new PromptSupport();
         PromptSupport prompt3 = new PromptSupport();
         prompt1.setPrompt("Radio Station...", insertRadioStationNameTextField);
         prompt2.setPrompt("Stream URL...", insertStreamURLTextField);
         prompt3.setPrompt("Genre...", insertGenreTextField);

         insertGenreTextField.setOpaque(false);
         insertStreamURLTextField.setOpaque(false);
         insertRadioStationNameTextField.setOpaque(false);

         setContentPane(this.panel1);
         pack();
         setSize(500,300);
         setResizable(false);

         try{
            countries = countries();
         }
         catch (IOException ioE1){
         }
         try{
            language = lang();
         }
         catch (IOException ioE2){
         }

         for(int i = 0; i<countries.length; i++){
            CountryBox.addItem(countries[i].toString());
         }
         for(int l = 0; l<language.length; l++){
            LangBox.addItem(language[l].toString());
         }
         setVisible(true);

         enterButton.addActionListener(new ActionListener()
               {
                       @Override
                       public void actionPerformed(ActionEvent e)
                       {
                          player.setVolume(0);

                          JOptionPane.showMessageDialog(customStationFrame.this, "Please Wait. Loading Stream URL.", "", JOptionPane.INFORMATION_MESSAGE, loadIcon);

                          /*final JDialog dialog = new JDialog();
                          //dialog.setTitle("");
                          dialog.setModal(true);
                          dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                          dialog.setContentPane(optionPane);
                          dialog.pack();

                          dialog.setVisible(true);*/

                          if((player.getOurMediaPlayer().startMedia(insertStreamURLTextField.getText().toString()) == false) || (CountryBox.getSelectedItem().toString().contains("No Country Selected")) || (insertRadioStationNameTextField.getText().equals(null))
                             || (insertStreamURLTextField.getText().equals(null)) || (LangBox.getSelectedItem().toString().contains("No Language Selected")) || (insertRadioStationNameTextField.getText().equals(null))
                             || (insertStreamURLTextField.getText().equals("Insert Stream URL"))){
                                 player.getOurMediaPlayer().stop();
                                 JOptionPane.showMessageDialog(customStationFrame.this, "Please check your entries and try again.", "Error Message", JOptionPane.PLAIN_MESSAGE , icon);
                          }
                          else{
                                try{
                                    editXLXS();
                                    dispose();
                                }
                                catch (IOException ioE){
                                }
                                enter.doClick();
                          }


                           }
                      });
   }

   public boolean isAdded(){
      return true;
   }

   public JButton getEnter() {
      return enter;
   }

   public void editXLXS() throws IOException{
       InputStream inp = new FileInputStream(System.getProperty("user.dir") + "/resources/AP Comp Sci - Radio Stations.xlsx");
       XSSFWorkbook wb = new XSSFWorkbook(inp);
       XSSFSheet sheet = wb.getSheetAt(0);
       int numRows = sheet.getPhysicalNumberOfRows();
       XSSFRow row = sheet.createRow(numRows);
       XSSFCell countryName = row.createCell(0);
       XSSFCell stationName = row.createCell(1);
       XSSFCell streamURL = row.createCell(2);
       XSSFCell lang = row.createCell(3);
       XSSFCell genre = row.createCell(4);
       XSSFCell fav = row.createCell(5);

       String cellContentsCountry = countryName.getStringCellValue();
       String cellContentsStation = stationName.getStringCellValue();
       String cellContentsURL = streamURL.getStringCellValue();
       String cellContentsLang = lang.getStringCellValue();
       String cellContentsGenre = genre.getStringCellValue();
       String cellContentsFav = fav.getStringCellValue();
      
       cellContentsCountry = CountryBox.getSelectedItem().toString();
       cellContentsStation = insertRadioStationNameTextField.getText();
       cellContentsURL = insertStreamURLTextField.getText();
       cellContentsLang = LangBox.getSelectedItem().toString();
       cellContentsGenre = insertGenreTextField.getText();
       if(!((cellContentsGenre.contains("Music")) || (cellContentsGenre.contains("News")) || (cellContentsGenre.contains("Sports")))){
            cellContentsGenre = "General";
       }
       cellContentsFav = "F";

       countryName.setCellValue(cellContentsCountry);
       stationName.setCellValue(cellContentsStation);
       streamURL.setCellValue(cellContentsURL);
       lang.setCellValue(cellContentsLang);
       genre.setCellValue(cellContentsGenre);
       fav.setCellValue(cellContentsFav);

       FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "/resources/AP Comp Sci - Radio Stations.xlsx");
       wb.write(fileOut);
       fileOut.close();
   }

   public String[] countries() throws IOException{
       InputStream inp = new FileInputStream(System.getProperty("user.dir") + "/resources/Countries.xlsx");
       XSSFWorkbook wb = new XSSFWorkbook(inp);
       XSSFSheet sheet = wb.getSheetAt(0);
       int numRows = sheet.getPhysicalNumberOfRows();
       String[] a = new String[numRows];
       for(int i = 0; i<numRows; i++){
          XSSFRow row = sheet.getRow(i);
          XSSFCell country = row.getCell(0);
          a[i] = country.getStringCellValue();
       }
       return a;
   }
   public String[] lang() throws IOException{
       InputStream inp = new FileInputStream(System.getProperty("user.dir") + "/resources/Countries.xlsx");
       XSSFWorkbook wb = new XSSFWorkbook(inp);
       XSSFSheet sheet = wb.getSheetAt(0);
       int numRows = 101;
       String[] a = new String[numRows];
       for(int i = 0; i<numRows; i++){
          XSSFRow row = sheet.getRow(i);
          XSSFCell lang = row.getCell(1);
          a[i] = lang.getStringCellValue();
       }
       return a;
   }

   public static void main(String[] args){
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
       customStationFrame frame = new customStationFrame();
   }

}
