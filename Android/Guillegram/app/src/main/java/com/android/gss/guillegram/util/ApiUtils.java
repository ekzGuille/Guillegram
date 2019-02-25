package com.android.gss.guillegram.util;

import com.android.gss.guillegram.model.api.controllerI.ApiServiceI;

public class ApiUtils {

    private ApiUtils(){}
    private static final String API_URL = "http://" + IPGetter.getInstance().getIP() + ":8080/api/";

    public static ApiServiceI getAPIService(){
        return RetrofitClient.getClient(API_URL).create(ApiServiceI.class);
    }
}
