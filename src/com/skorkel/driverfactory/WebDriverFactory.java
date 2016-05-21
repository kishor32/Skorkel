package com.skorkel.driverfactory;
import com.skorkel.Utility.LoadProperties;
import com.skorkel.browser.Browser;
import com.skorkel.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;

/**
 * Created by kishor on 4/29/2016.
 */
public class WebDriverFactory {

    private WebDriverFactory(){}

    private static WebDriverFactory wf=new WebDriverFactory();

    public static WebDriverFactory getInstance(){

        return wf;
    }

    WebDriver driver;
    Browser browser=Browser.getInstance();
    public void  setDriver() {
        String browsernName=null;
        WebDriver driver=null;
        try {
            browsernName= browser.getBrowSer().toLowerCase();
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        Log.info("browser name is:::"+browsernName);

        switch (browsernName) {
            case "firefox":
                driver = new FirefoxDriver();
                this.driver = driver;
                Log.info("Browser initiated:::"+browsernName);
                break;
            case "chrome":
                setOtherBroserDriver("chrome");
                driver = new ChromeDriver();
                this.driver = driver;
                Log.info("Browser initiated:::"+browsernName);
                break;
            case "ie-11":
                setOtherBroserDriver("ie-11");
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(capabilities);
                this.driver = driver;
                Log.info("Browser initiated"+browsernName);
                break;
            default:
                Log.error("No valid Browser Found.Test can not be run");
                System.exit(1);
        }
    }

        public WebDriver getDriver(){

            return driver;
        }

        protected void setOtherBroserDriver(String driverName) {
            switch (driverName.toLowerCase()) {
                case "chrome":
                    loadChrome();
                    break;
                case "ie-11":
                    loadIE();
                    break;
            }
        }


        protected  void loadChrome(){
            File chrome=new File(LoadProperties.load().getProperty("ChromeDrvLoc"));
            System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        }

        protected  void loadIE(){
            File IE=new File(LoadProperties.load().getProperty("IEDriverLoc"));
            System.setProperty("webdriver.ie.driver", IE.getAbsolutePath());
    }

}

