package com.skorkel.browser;


/**
 * Created by kishor on 4/29/2016.
 */
public class Browser {

    private Browser(){}
    private static Browser br=new Browser();

    public static Browser getInstance(){
        return br;
    }

    String browser=null;
    public  void setBrowSer(String browser){
        this.browser=browser;
    }

    public  String getBrowSer(){
       return browser;
    }

}
