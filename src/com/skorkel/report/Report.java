package com.skorkel.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.skorkel.Utility.LoadProperties;
import java.time.LocalDateTime;

/**
 * Created by kishor on 4/29/2016.
 */
public class Report {
    ExtentReports report;
    private static Report ourInstance = new Report();

    public static Report getInstance() {
        return ourInstance;
    }

    private Report() {}


    public void setReport(){
        LocalDateTime ldt = LocalDateTime.now();
        String dirtectoryName="Report-"+ldt.getDayOfMonth()+"-"+ldt.getMonthValue()+"-"+ldt.getYear()+"-"+ldt.getHour()+"-"+ldt.getMinute()+"-"+ldt.getSecond();
        report=new ExtentReports(LoadProperties.load().getProperty("Report")+"//"+dirtectoryName+"//report.html",true);

    }

    public ExtentReports getReport(){

        return report;
    }

    public void flush(){

        this.getReport().flush();
    }

}
