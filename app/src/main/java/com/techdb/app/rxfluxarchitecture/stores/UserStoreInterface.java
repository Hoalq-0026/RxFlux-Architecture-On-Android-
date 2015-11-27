package com.techdb.app.rxfluxarchitecture.stores;

import com.techdb.app.rxfluxarchitecture.models.GitUser;

import java.util.ArrayList;

/**
 * Created by HoaLQ on 11/24/15.
 */
public interface UserStoreInterface {
    GitUser getUser(String id);

    ArrayList<GitUser> getUsers();
}
