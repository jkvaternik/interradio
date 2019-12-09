package meme;

import Window.StationPanel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by alexr on 12/5/2016.
 */
public class xlxs {

    protected String[][] streams;
    public xlxs() throws IOException{
        streams = readXLSX();
    }
    public String[][] getStreams(){
        return streams;
    }
    public boolean checkXLXS() throws IOException{
        int num = 101;
        InputStream excelFile = new FileInputStream(System.getProperty("user.dir") + "/resources/AP Comp Sci - Radio Stations.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = wb.getSheetAt(0);
        int numRows = sheet.getPhysicalNumberOfRows();
        if(numRows > num){
            return true;
        }
        return false;
    }

    public static String[][] readXLSX() throws IOException {
        InputStream excelFile = new FileInputStream(System.getProperty("user.dir") + "/resources/AP Comp Sci - Radio Stations.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = wb.getSheetAt(0);
        int numRows = sheet.getPhysicalNumberOfRows();
        String[][] streams = new String[numRows][6];

        for(int i = 0; i<numRows; i++) {
            XSSFCell country = sheet.getRow(i).getCell(0);
            XSSFCell radioStation = sheet.getRow(i).getCell(1);
            XSSFCell streamURL = sheet.getRow(i).getCell(2);
            XSSFCell lang = sheet.getRow(i).getCell(3);
            XSSFCell genre = sheet.getRow(i).getCell(4);
            XSSFCell fav = sheet.getRow(i).getCell(5);
            if (!(streamURL.getStringCellValue() == null)){
                streams[i][0] = country.getStringCellValue();
                }
            if (!(streamURL.getStringCellValue() == null)){
                streams[i][1] = radioStation.getStringCellValue();
                }
            if (!(streamURL.getStringCellValue() == null)){
                streams[i][2] = streamURL.getStringCellValue();
                }
            if (!(streamURL.getStringCellValue() == null)){
                streams[i][3] = lang.getStringCellValue();
                }
            if (!(streamURL.getStringCellValue() == null)){
                streams[i][4] = genre.getStringCellValue();
                }
            if (!(streamURL.getStringCellValue() == null)){
                streams[i][5] = fav.getStringCellValue();
                }
        }
        return streams;
    }

    public static void main(String[] args) throws IOException{
        String[][] a = readXLSX();
        System.out.println(a);
    }
}