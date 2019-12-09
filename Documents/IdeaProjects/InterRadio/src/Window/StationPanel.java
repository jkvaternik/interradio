package Window;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alex on 12/12/2016.
 */
//A panel to hold the station name, play button, star button, and info button inside the CountryPanel
public class StationPanel extends JPanel {
    protected StationPanelProperties properties;
    protected JLabel stationName = new JLabel();
    protected AddNewStationButton newStationButton = new AddNewStationButton();
    protected JButton update = new JButton();
    protected JButton faved = new JButton();

    public StationPanel() {
        super();
        //noStation();
    }

    public void setProperties(StationPanelProperties p) {
        properties = p;
        if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0){
        properties.setBigButtons(false);
        }
        if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0){
        properties.setBigButtons(true);
        }
        updatePanel();
    }

    public void updatePanel() {
        removeAll();
        stationName.setText(properties.getStationName());
        if (properties.isBigButtons()) {
            stationName.setFont(stationName.getFont().deriveFont(24.0f));
        }
        add(stationName);
        add(properties.getPlayButton());
        add(properties.getStarButton());
        add(properties.getInfoButton());
        repaint();
        revalidate();
        properties.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update.doClick();
            }
        });
        properties.getStarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    editXLXSFavs();
                    faved.doClick();
                }
                catch (IOException ioE){
                }
            }
        });
    }

    public StationPanelProperties getProperties() {
        return properties;
    }

    public void noStation() {
        add(newStationButton);
    }

    public void editXLXSFavs() throws IOException {
       InputStream inp = new FileInputStream(System.getProperty("user.dir") + "/resources/AP Comp Sci - Radio Stations.xlsx");
       XSSFWorkbook wb = new XSSFWorkbook(inp);
       XSSFSheet sheet = wb.getSheetAt(0);

       int numRows = sheet.getPhysicalNumberOfRows();
       for(int i = 0; i<numRows; i++){
          XSSFCell radioStation = sheet.getRow(i).getCell(1);
          if(properties.getStationName().equals(radioStation.getStringCellValue())){
                XSSFCell fav = sheet.getRow(i).getCell(5);
                if (fav.getStringCellValue().equals("F")) {
                    fav.setCellValue("T");
                }
                else {
                    fav.setCellValue("F");
                }
          }
       }
       FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "/resources/AP Comp Sci - Radio Stations.xlsx");
       wb.write(fileOut);
       fileOut.close();
   }

    public JButton getUpdate() {
        return update;
    }

    public JButton getFaved() {
        return faved;
    }
}
