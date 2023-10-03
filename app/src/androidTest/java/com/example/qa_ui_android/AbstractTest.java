package com.example.qa_ui_android;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String ssidName;

    @Before
    public void initTest() throws IOException {
//        configFile = new FileInputStream("src/main/res/my.properties");
//        prop.load(configFile);

        ssidName = "_P_metro";

    }

    public static String getSsid() {
        return ssidName;
    }


}
