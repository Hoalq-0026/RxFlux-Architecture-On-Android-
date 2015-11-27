package com.techdb.app.rxfluxarchitecture.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techdb.app.rxfluxarchitecture.AppConfig;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by HoaLQ on 11/23/15.
 */
public class GitHubAppService {
    private static GitHubAppApi mRxSampleAppApi;
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static void build() {
        final Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT_PATTERN).registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create();
        final GsonConverter converter = new GsonConverter(gson);
        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AppConfig.BASE_URL).setConverter(converter).setLogLevel(RestAdapter.LogLevel.FULL).build();
        mRxSampleAppApi = restAdapter.create(GitHubAppApi.class);
    }

    public static synchronized GitHubAppApi getRxSampleAppApi() {
        if (mRxSampleAppApi == null) {
            build();
        }
        return mRxSampleAppApi;
    }
}
