package com.skorkel.uioperation;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.skorkel.Utility.LoadProperties;
import com.skorkel.driverfactory.WebDriverFactory;
import com.skorkel.webdriverutil.WebDriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import com.skorkel.logging.Log;
import org.openqa.selenium.support.ui.Wait;
import com.google.common.base.Function;
/**
 * Created by kishor on 4/29/2016.
 */
public class UIInteraction {
    static WebDriver driver=WebDriverFactory.getInstance().getDriver();

    public static WebElement findElement(By Locator){
        WebElement element=null;
        Wait<WebDriver> wait=new FluentWait<>(driver)
                .pollingEvery(5, TimeUnit.SECONDS)
                .withTimeout(30, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            element=wait.until(webDriver->webDriver.findElement(Locator));
        }catch(ElementNotFoundException  e){
            Log.error("Element not found exception"+e.getMessage());
        }
        return element;

    }

    public static List<WebElement> findElements(String tagName){
        List<WebElement> elements=driver.findElements(By.tagName(tagName));
        Log.info("List of Element Found");
        if(elements.size()==0){
            System.out.println("No element found By this tag name-->"+tagName);
            Log.error("Element not found exception");
        }
        return elements;
    }

    public static void sendData(WebElement element , String data){
        element.sendKeys(data);
        Log.info("Data sent to text field");
    }

    public void senddataToAjaxfiled(WebElement element , String data){
        element.sendKeys(data);
        try {
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.getCause();
            Log.error("InterruptedException in Thread.sleep");
        }
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.RETURN);
        Log.info("Data sent to Ajaax text field");
    }

    public static void selectDropDownBytext(WebElement element, String text){
        Select select=new Select(element);
        select.selectByVisibleText(text);
    }

    public static void mouseClick(WebElement element){
        try{
            element.click();
        }catch (StaleElementReferenceException e){
            throw new StaleElementReferenceException("Some Shit is happening");
        }
    }

    public static String getMainWindowHandler(){
        return driver.getWindowHandle();
    }

    public static void swithchTomainWindow(String mainWindowHandle){
        driver.switchTo().window(mainWindowHandle);
    }

    /*Some time we need custom logic to handle javascript window/Jquery pop ups*/
    public static Boolean  handlePopup(String mainWindowHandle){
        boolean isHandled=false;
        Set<String> handles=null;
        try {
            handles= driver.getWindowHandles();
        }catch(NoSuchWindowException e){
            e.printStackTrace();
            Log.error("No such window Presents.."+e.getMessage());
        }
        handles.remove(mainWindowHandle);
        Iterator<String> iterator = handles.iterator();
        while(iterator.hasNext()){
            String winHandle=iterator.next();
            driver.switchTo().window(winHandle);
            Log.info("pop up handled");
            isHandled=true;
        }
        return isHandled;
    }

    public static String screenShot(String testCasename){

        LocalDateTime date=LocalDateTime.now();
        OutputStream f=null;
        String filename=testCasename+"_image"+String.valueOf(date.getSecond()+".png");
        byte [] fileByte=WebDriverUtil.takeScreenshots().getScreenshotAs(OutputType.BYTES);
        String path=LoadProperties.load().getProperty("imagePath")+"/"+filename;
        try {
            f=new FileOutputStream(path);
            f.write(fileByte);
            Log.info("Screen shot created::"+filename);
        }catch (IOException e){
            Log.info("Error taking screenshot");
        }
        return path;
    }

}
