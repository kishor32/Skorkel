package com.skorkel.webdriverutil;

import com.skorkel.driverfactory.WebDriverFactory;
import com.skorkel.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Created by kishor on 4/29/2016.
 */
public class WebDriverUtil {
    static WebDriver driver=WebDriverFactory.getInstance().getDriver();

    public static WebDriverWait explictWait(int time){
        Log.info(" Object of WebDriverWait created");
        return new WebDriverWait(driver,time);
    }

    public static Actions advanceInteraction(){
        Log.info(" Object of Actions class is created");
        return new Actions(driver);
    }

    public static void  tearDown(){
        driver.quit();
    }

    public static EventFiringWebDriver takeScreenshots(){
        return new EventFiringWebDriver(driver);
    }
}
