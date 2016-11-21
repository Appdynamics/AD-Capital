package com.appdynamics.loan.processor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by swetha.ravichandran on 7/12/15.
 */
public class GetConfigProperties {

    public String getRabbitMQUrl() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        // get the property value and print it out
        String orderUrl = prop.getProperty("mqurl");
        return orderUrl;
    }
}
