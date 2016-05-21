package com.skorkel.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by kishor on 4/29/2016.
 */
public abstract class LoginRepo {

    public static  By logInLink =  By.id("lnklogin");
    public static  By userName =   By.id("UserName");
    public static  By password =   By.id("Password");
    public static  By loginButton =By.id("button");
}
