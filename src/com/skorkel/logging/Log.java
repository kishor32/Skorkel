package com.skorkel.logging;
import org.apache.log4j.Logger;
/**
 * Created by kishor on 4/29/2016.
 */
public class Log {

    private static  Logger Log=Logger.getLogger(Log.class.getName());
    public static void startTestCase(String testCase){
        Log.info("$$$$$$$$$$$$-START-$$$$$$$$$$$$");
        Log.info(testCase);
    }

    public static void endTestCase(String testCase){
        Log.info("$$$$$$$$$$$$-END-$$$$$$$$$$$$");
        Log.info(testCase);
    }

    public static void info(String msg){
        Log.info(msg);
    }

    public static void warn(String msg){
        Log.warn(msg);
    }

    public static void error(String msg){

        Log.error(msg);
    }

    public static void debug(String msg){
        Log.debug(msg);
    }
}
