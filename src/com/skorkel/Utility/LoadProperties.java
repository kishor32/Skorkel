package com.skorkel.Utility;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
/**
 * Created by kishor on 4/28/2016.
 */
public  class LoadProperties {

    private LoadProperties(){
        // don't dare to create object
    }
    private static Properties props;
    public static Properties load(){
        try {
            FileInputStream file = new FileInputStream("Settings.properties");
            props=new Properties();
            props.load(file);
        }catch(IOException fileNotFound){
            fileNotFound.printStackTrace();
        }
        return props;
    }
}
