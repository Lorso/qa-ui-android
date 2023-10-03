package com.example.qa_ui_android;

import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest extends AbstractTest {
    UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Test
    //Метод перехода на экран Домой
    public void goHome() throws RemoteException {
        if (!device.isScreenOn()) {
            device.wakeUp();
            Log.d("NothingTest", "Включен экран");
            device.pressHome();
            Log.d("NothingTest", "Нажата кнопка Home");

        } else {
            device.pressHome();
            Log.d("NothingTest", "Нажата кнопка Home");
        }
    }

    @Test
    //Метод подключения к ТД по SSID
    public void connectToAP() throws RemoteException, IOException, InterruptedException {
        //initTest();
        goHome();
        device.executeShellCommand("am start -n com.steinwurf.adbjoinwifi/.MainActivity -e ssid " + getSsid());
        System.out.println("Запущен процесс подключения к ТД");
        UiObject2 wifiTrayElement = device.wait(Until.findObject(By.res("com.android.systemui:id/wifi_combo")), 30000);
        device.executeShellCommand("am force-stop com.steinwurf.adbjoinwifi");

        if (wifiTrayElement != null) {
            Log.d("NothingTest", "Локатор подключения к ТД найден");
        }
        System.out.println(wifiTrayElement);
        assertNotNull("Ассоциации с ТД нет", wifiTrayElement);
        Log.d("NothingTest", "Успешная ассоциация с ТД");
    }

    @Test
    //Метод открытия настроек WiFi сетей
    public void openWiFiSettings() throws RemoteException, IOException, InterruptedException {
        goHome();
        device.executeShellCommand("am start -a android.settings.WIFI_SETTINGS");
        Log.d("NothingTest", "Открыты настройки WiFi сетей");
        Thread.sleep(5000);
    }

    @Test
    //Метод сброса настроек сети
    public void resetWiFiSettings() throws RemoteException, IOException, InterruptedException {
        goHome();
        device.executeShellCommand("am start -a android.settings.SETTINGS");
        Log.d("NothingTest", "Открыты настройки");
        UiObject2 searchSettings = device.wait(Until.findObject(By.res("com.android.settings:id/search_action_bar_title")), 5000);
        searchSettings.click();
        UiObject2 searchSettingsText = device.wait(Until.findObject(By.res("android:id/search_src_text")), 5000);
        searchSettingsText.setText("Сброс");
        device.wait(Until.findObject(By.text("Сбросить настройки Wi-Fi, мобильного интернета и Bluetooth")), 5000).click();
        Thread.sleep(2000);
        device.wait(Until.findObject(By.text("Сбросить настройки Wi-Fi, мобильного интернета и Bluetooth")), 5000).click();
        device.wait(Until.findObject(By.text("СБРОСИТЬ НАСТРОЙКИ")), 5000).click();
        device.wait(Until.findObject(By.text("ДА")), 5000).click();
        Log.d("NothingTest", "Настройки сети сброшены");
        Thread.sleep(10000);
    }
    @Test
    //Метод переключения на статичный мак адрес
    public void changeMacToStatic() throws IOException, RemoteException, InterruptedException, UiObjectNotFoundException {
        goHome();
        openWiFiSettings();
        UiObject shesterenka = device.findObject(new UiSelector()
                .className("android.widget.LinearLayout")
                .index(2));
        if (shesterenka.exists()) {
            shesterenka.click();
        }
        Thread.sleep(1000);
        device.wait(Until.findObject(By.text("Конфиденциаль­ность")), 5000).click();
        device.wait(Until.findObject(By.text("MAC-адрес устройства")), 5000).click();
        Log.d("NothingTest", "MAC-адрес устройства сменился на статичный");

    }
    @Test
    // Проверка всплытия каптивы
    public void checkCaptive(){
        UiObject2 treeDots = device.wait(Until.findObject(By.desc("Ещё")), 15000);
        if (treeDots != null) {
            Log.d("NothingTest", "Кэптив всплыл");
        }
        assertNotNull("Кэптив не всплыл", treeDots);
    }
    @Test
    public void passingAuth(){


    }

}