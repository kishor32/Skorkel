package com.skorkel.Utility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;
import com.skorkel.logging.Log;
/**
 * Created by kishor on 4/28/2016.
 */

public class ExcelUtility {

    public  static FileInputStream excelFile(FileInputStream file, String filePath){
        try {
            file = new FileInputStream(filePath);
            if(file!=null){
                Log.info("Excel File found");
            }
        }catch (IOException fileNotFound){
            fileNotFound.printStackTrace();
            Log.error("File not found exception");
        }
        return file;
    }


    public static XSSFSheet getSheet(FileInputStream file, String sheetName){
        XSSFWorkbook wb=null;
        try {
            wb=new XSSFWorkbook(file);
            Log.info("A new excel workbook created");
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("IOException Occurred");
        }
        XSSFSheet sheet=wb.getSheet(sheetName);
        return sheet;
    }

    public static int getnumberofRow(XSSFSheet sheet){
        return sheet.getPhysicalNumberOfRows()-1;
    }

    public static String getCellData(XSSFSheet sheet, int row, int column ) {

        String cellData=null;
            Cell cell= sheet.getRow(row).getCell(column);
            if(cell!=null) {
                switch (cell.getCellType()) {
                    case CELL_TYPE_BLANK:
                        break;
                    case CELL_TYPE_NUMERIC:
                        cellData = String.valueOf((long) cell.getNumericCellValue());
                        break;
                    case CELL_TYPE_STRING:
                        cellData = cell.getStringCellValue();
                        break;
                }
              }
        return cellData.trim();
    }
}
