package com.skorkel.bussinesslogic;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.skorkel.Utility.LoadProperties;
import com.skorkel.browser.Browser;
import com.skorkel.driverfactory.WebDriverFactory;
import com.skorkel.logging.Log;
import com.skorkel.objectrepository.LoginRepo;
import com.skorkel.report.Report;
import com.skorkel.session.Session;
import com.skorkel.uioperation.UIInteraction;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by kishor on 4/29/2016.
 */
public class A_Login {
    WebElement loginLink=null;
    ExtentTest test;
    WebDriverFactory driver=WebDriverFactory.getInstance();
    Report report=Report.getInstance();
    Browser browser=Browser.getInstance();

    @Test(priority=0)
    public void invokeApp(){
        driver.setDriver();
        Log.info("WebDriver instance is set");
        String appUrl=LoadProperties.load().getProperty("AppUrl");
        driver.getDriver().get(appUrl);
        Log.info("App is invoked:::"+appUrl);

        /*Extra check point for IE since IE is shit*/
        String browserName=browser.getBrowSer();
        if(browserName.equals("IE-11")){
            System.out.println("waiting........");
            try {
                Thread.sleep(4000);
                Log.info("Stopping the main thread for a while ");
            }catch(InterruptedException e){
                Log.error(e.getMessage());
            }
        }else{
            driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Log.info("Waiting for 10 Seconds");
        }
        driver.getDriver().manage().window().maximize();
        test=report.getReport().startTest("Invoke app",LoadProperties.load().getProperty("AppUrl"));
        report.flush();
        Log.info("Browser get Maximized");
    }


    @Test(priority=1)
    public void findSigninButton(){
        Log.startTestCase("Find Sign In Button");
        loginLink=UIInteraction.findElement(LoginRepo.logInLink);
        if(loginLink!=null){
            test.log(LogStatus.PASS,"loginLink Button Found::findSigninButton");
            report.flush();
        }
        UIInteraction.mouseClick(loginLink);
        Log.info("Log in Link button is clicked");

    }

    @Test(priority=2)
    public void verifyPopup(){
        String mainWindowHandle=UIInteraction.getMainWindowHandler();
        if(UIInteraction.handlePopup(mainWindowHandle)){
            UIInteraction.swithchTomainWindow(mainWindowHandle);
            test.log(LogStatus.PASS,"pop up verified::verifyPopup");
            Log.info("pop up handled");
            Report.getInstance().flush();
        }else{
            test.log(LogStatus.FAIL,"pop up not verified::");
            Log.info("pop up not handled properly");
            Report.getInstance().flush();
        }

    }

    @Test(priority=3)
    public void findandsendUsername(){
        WebElement username=UIInteraction.findElement(LoginRepo.userName);
        if(username!=null){
            UIInteraction.sendData(username,"kishor+6@atlogys.com");
            test.log(LogStatus.PASS,"Username found and user name send:findandsendUsername");
        }else{
            test.log(LogStatus.FAIL,"Username filed not found");
        }
        Report.getInstance().flush();
    }

    @Test(priority=4)
    public void findandsendPassword(){
        WebElement password=UIInteraction.findElement(LoginRepo.password);
        if(password!=null){
            UIInteraction.sendData(password,"password@34");
            test.log(LogStatus.PASS,"Password found and Password send:findandsendPassword");
        }else{
            test.log(LogStatus.FAIL,"Password filed not found");
        }
        Report.getInstance().flush();
    }

    @Test(priority=5)
    public void clickloginButton(){
        WebElement loginButton=UIInteraction.findElement(LoginRepo.loginButton);
        UIInteraction.mouseClick(loginButton);
        test.log(LogStatus.PASS,"Log In done:clickloginButton");
        Log.info("Logged In..");
        Log.endTestCase("Test Execution Ends");
        report.flush();
        report.getReport().endTest(test);
        Session.Login=true;
    }
}
