package com.skorkel.driver;
import java.io.File;
import java.io.FileInputStream;
import com.skorkel.Utility.ExcelConstants;
import com.skorkel.Utility.ExcelUtility;
import com.skorkel.Utility.LoadProperties;
import com.skorkel.browser.Browser;
import com.skorkel.logging.Log;
import com.skorkel.report.Report;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.testng.annotations.Factory;

/**
 * Created by kishor on 4/28/2016.
 */
public class App {
    static FileInputStream fin;
    static List<String> data=new LinkedList<>();
    static List<String> keywords=new LinkedList<>();

    public  static FileInputStream getFile(){
        String filePath= LoadProperties.load().getProperty("RunManagerPath");
        return ExcelUtility.excelFile(fin,filePath);
    }

    @Factory
    public  Object[] executeTestCases()
            throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            InvocationTargetException {

        this.setlogFile(); /* Set up log file*/
        this.setReport(); /* Set up Report*/
        List<Object> testApp=new ArrayList<>(); /* A list for testNG*/
        List<String> keywords=getKyewords();   /* Storing all keywords from excel sheet*/
        for(String s:keywords){
            System.out.println("====::"+s);
        }
        final String CLASS_FILE_EXTENSION = ".class";
        String className = null;
        File packageDirectories = new File(LoadProperties.load().getProperty("PackageDir"));
        File[] directory = packageDirectories.listFiles();
        for (int i = 0; i < directory.length; i++) {
            File packageFile = directory[i];
            String fileName = packageFile.getName();
            if (fileName.endsWith(CLASS_FILE_EXTENSION)) {
                className = fileName.substring(0, fileName.length()
                        - CLASS_FILE_EXTENSION.length());
            }

            Class<?> testCase = Class.forName("com.skorkel.bussinesslogic" + "." + className);
            Object Obj = testCase.newInstance();
            Method[] methods = Obj.getClass().getMethods();
            for (String s : keywords) {
                for (Method m : methods) {
                    if (s.equalsIgnoreCase(m.getName())) {
                        System.out.println(m.getName());
                        testApp.add(Obj);
                    }
                }
            }
        }
        Object[] data = testApp.toArray();
        return data;
    }

    public List<String> getKyewords() {

        XSSFSheet Sheet_1 = ExcelUtility.getSheet(getFile(), ExcelConstants.SHEET_1);
        Log.info("First excel sheet opened");
        XSSFSheet Sheet_2 = ExcelUtility.getSheet(getFile(), ExcelConstants.SHEET_2);
        Log.info("Second excel sheet opened");
        XSSFSheet Sheet_3 = ExcelUtility.getSheet(getFile(), ExcelConstants.SHEET_3);
        Log.info("Third excel sheet opened");

        /*Read Browser name from Sheet 3 of Run manager file*/
        Browser.getInstance().setBrowSer(ExcelUtility.getCellData(Sheet_3, 1, 0));
        Log.info("Set Browser from excel");

        /*Get row number from Sheet 1*/
        int numberRows_Sheet1 = ExcelUtility.getnumberofRow(Sheet_1);
        /*Get row number from Sheet 2*/
        int numberRows_Sheet2 = ExcelUtility.getnumberofRow(Sheet_2);

        /*get all Cases That are marked Yes from 1st Sheet*/
        Log.info("get all Cases That are marked Yes from 1st Sheet");
        for (int tempVar = 1; tempVar <= numberRows_Sheet1; tempVar++) {
            if (ExcelUtility.getCellData(Sheet_1, tempVar, 3).equalsIgnoreCase("Yes")) {
                data.add(ExcelUtility.getCellData(Sheet_1, tempVar, ExcelConstants.TC_ID));
            }
        }

        /*get all keywords that related to the test cases which are marked Yes*/
        Log.info("get all keywords that related to the test cases which are marked Yes");
        for (int tempVar = 1; tempVar <= numberRows_Sheet2; tempVar++) {
            if (data.contains(ExcelUtility.getCellData(Sheet_2, tempVar, ExcelConstants.TC_ID))) {
                keywords.add(ExcelUtility.getCellData(Sheet_2, tempVar, ExcelConstants.TC_ID + 1));
            }
        }
        return keywords;
    }

    private void setlogFile(){
        DOMConfigurator.configure("log.xml");
    }

    private void setReport(){
        Report.getInstance().setReport();
    }
}
