package com.example.qa_ui_android;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpRequests {
    @Test
    public void dropSession() throws IOException {
        String url = "http://sae.msk.vmet.ro/v1/drop/mac/2c-be-eb-22-32-76";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            // Далее можно провести проверки ответа, если это необходимо.
        }
    }

}
