package com.skorkel.objectrepository;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;

/**
 * Created by kishor on 5/1/2016.
 */
public abstract class HomeRepo {

    public static By txtPostUpdate= By.id("txtPostUpdate");
    public static By lnkPostUpdate=By.id("lnkPostUpdate");
    public static By FileUplogo=By.id("FileUplogo");
    public static By documentLink=By.id("ctl00_ContentPlaceHolder1_lnkDocuments");
    public static By DoctabHeading=By.className("newGroupHeading");
    public static By workExp=By.id("ctl00_ContentPlaceHolder1_lnkWorkex");
    public static By workExptabHeading=By.className("newGroupHeading");
    public static By education=By.id("ctl00_ContentPlaceHolder1_lnkEducation");
    public static By achievements=By.id("ctl00_ContentPlaceHolder1_lnkAchievements");


}
