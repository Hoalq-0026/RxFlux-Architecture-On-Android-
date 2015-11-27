package com.techdb.app.rxfluxarchitecture.stores;

import com.techdb.app.rxfluxarchitecture.models.GitHubRepo;

import java.util.ArrayList;

/**
 * Created by HoaLQ on 11/23/15.
 */
public interface RepositoriesStoreInterface {
    ArrayList<GitHubRepo> getRepositories();
}
