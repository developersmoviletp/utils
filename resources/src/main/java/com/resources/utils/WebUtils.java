package com.resources.utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;

/**
 * Created by mahegots on 15/03/18.
 * TeamMobileTP
 */

public class WebUtils {
    public static OkHttpClient.Builder interceptorDeveloperMode(OkHttpClient.Builder builder) {
        builder.hostnameVerifier((hostname, session) -> {
            HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            return hostnameVerifier.verify(hostname, session);
        });
        return builder;
    }
}
