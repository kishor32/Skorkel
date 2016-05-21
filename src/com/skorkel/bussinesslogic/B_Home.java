package com.skorkel.bussinesslogic;
import com.skorkel.objectrepository.HomeRepo;
import static com.skorkel.uioperation.UIInteraction.*;
import java.lang.Object;
import static com.skorkel.session.Session.Login;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Created by kishor on 5/1/2016.
 */
public class B_Home {

    @Test(priority =6)
    public void shareUpdate(String statusUpdate) {
        if (Login) {
            WebElement txtPostUpdate = findElement(HomeRepo.txtPostUpdate);
            WebElement lnkPostUpdate = findElement(HomeRepo.lnkPostUpdate);
            mouseClick(txtPostUpdate);
            sendData(txtPostUpdate, statusUpdate);
            mouseClick(lnkPostUpdate);
        }
    }

    @Test(enabled=false)
    public void uploadMedia(){

    }

    @Test(enabled=false)
    public void changeName(){

    }

    @Test(enabled=false)
    public void checkWorkingLinks(){

    }

    @DataProvider(name = "gettestData")
    public static Object[][] gettestData(){
        Object [][]obj={{"222-AlphaNumeric"},{""}};
        return obj;
    }
}
