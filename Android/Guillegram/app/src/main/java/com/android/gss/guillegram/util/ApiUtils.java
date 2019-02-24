package com.android.gss.guillegram.util;

import com.android.gss.guillegram.model.api.controllerI.ApiControllerI;

public class ApiUtils {

    private ApiUtils(){}
    private static final String API_URL = "https://" + IPGetter.getInstance().getIP() + "/api/";

    public static ApiControllerI getApiUtils(){
        return RetrofitClient.getClient(API_URL).create(ApiControllerI.class);
    }
}
