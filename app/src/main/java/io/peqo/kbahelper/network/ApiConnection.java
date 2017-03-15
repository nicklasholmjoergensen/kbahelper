package io.peqo.kbahelper.network;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Api Connection used to load and return JSON data from web server.
 */

public class ApiConnection {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    private static final MediaType JSON = MediaType.parse(CONTENT_TYPE_VALUE_JSON);

    private URL url;
    private String response;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ApiConnection open(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    @Nullable
    public String syncGetRequest() {
        get();
        return response;
    }

    @Nullable
    public String syncPostRequest(String data) {
        post(data);
        return response;
    }

    private void get() {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        try {
            this.response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
        } catch (IOException e) {
            Log.d("DEBUG", "Error: " + e);
        }
    }

    private void post(String data) {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .post(RequestBody.create(JSON, data))
                .build();

        try {
            this.response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
        } catch(IOException e) {
            Log.d("DEBUG", "ERROR: " + e);
        }
    }

}
