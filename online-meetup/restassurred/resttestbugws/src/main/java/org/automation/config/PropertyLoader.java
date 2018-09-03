package org.automation.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by SSarker on 9/2/2018.
 */
public class PropertyLoader {
    public static void loadProperties() throws IOException {
        loadProperty("test.properties");

        if("dev".equals(System.getProperty("test.environment"))){
            loadProperty("app.dev.properties");
        }
        else if("qa".equals(System.getProperty("test.environment"))){
            loadProperty("app.qa.properties");
        }
        else if("prod".equals(System.getProperty("test.environment"))){
            loadProperty("app.prod.properties");
        }
        else {
            throw new RuntimeException(" No Suitable ENV property found ");
        }

    }
    public static void loadProperty(String propertyFileName) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(new File("./properties/"+propertyFileName)));
        for(String k:p.stringPropertyNames()){
            System.setProperty(k,p.getProperty(k));
        }
    }
}
