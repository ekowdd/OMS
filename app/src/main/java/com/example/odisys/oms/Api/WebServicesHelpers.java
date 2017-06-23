package com.example.odisys.oms.Api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Odisys on 03/04/2017.
 */

public class WebServicesHelpers {
    public static OkHttpClient mClient;
    public static void initialize(){
        mClient = new OkHttpClient();
        mClient.newBuilder().connectTimeout(200, TimeUnit.SECONDS).build();
    }

    public static String doPost(String url, RequestBody mBody)throws IOException{

        Headers headers = new Headers.Builder()
                .add("Aurhorization","Basic")
                .build();
        Request mRequest = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(mBody)
                .build();
        Response mResponse = mClient.newCall(mRequest).execute();
        return mResponse.body().string();
    }

    public static String doGet(String url, String token)throws IOException{
        Request mRequest = new Request.Builder()
                .addHeader("Authorization","Basic " + token)
                .url(url)
                .build();
        Response mResponse = mClient.newCall(mRequest).execute();
        return mResponse.body().string();
    }

}
