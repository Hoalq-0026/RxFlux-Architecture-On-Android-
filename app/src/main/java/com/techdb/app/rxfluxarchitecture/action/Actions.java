package com.techdb.app.rxfluxarchitecture.action;

/**
 * Created by HoaLQ on 11/23/15.
 */
public interface Actions {
    String GET_REPOSITORIES = "get_repositories";
    String GET_USER = "get_user";

    void getRepositories();

    void getUserDetail(String userId);
}
