package com.example.qa_ui_android;

import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiObjectNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class AuthTest extends AbstractTest {
    InstrumentedTest instrumentedTest = new InstrumentedTest();
    //HttpRequests httpRequests = new HttpRequests();

    @Before
    public void start() throws RemoteException, IOException {
        //httpRequests.dropSession();
        instrumentedTest.goHome();
    }
    @Test
    public void auth() throws RemoteException, IOException, InterruptedException, UiObjectNotFoundException {
        instrumentedTest.connectToAP();
        //instrumentedTest.openWiFiSettings();
        instrumentedTest.changeMacToStatic();
        instrumentedTest.checkCaptive();
    }
    @After
    public void reset() throws IOException, RemoteException, InterruptedException {
        instrumentedTest.resetWiFiSettings();
        instrumentedTest.goHome();
    }
}
