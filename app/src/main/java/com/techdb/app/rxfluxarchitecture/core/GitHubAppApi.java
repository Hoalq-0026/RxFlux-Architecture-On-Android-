package com.techdb.app.rxfluxarchitecture.core;

import com.techdb.app.rxfluxarchitecture.AppConfig;
import com.techdb.app.rxfluxarchitecture.models.GitHubRepo;
import com.techdb.app.rxfluxarchitecture.models.GitUser;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by HoaLQ on 11/23/15.
 */
public interface GitHubAppApi {
    String SERVICE_ENDPOINT = AppConfig.BASE_URL;

    @GET("/repositories")
    Observable<ArrayList<GitHubRepo>> getRepositories();

    @GET("/users/{id}")
    Observable<GitUser> getUser(@Path("id") String userId);
}
