package com.techdb.app.rxfluxarchitecture.stores;

import com.techdb.app.rxflux.action.RxAction;
import com.techdb.app.rxflux.dispatcher.Dispatcher;
import com.techdb.app.rxflux.store.RxStore;
import com.techdb.app.rxflux.store.RxStoreChange;
import com.techdb.app.rxfluxarchitecture.action.Actions;
import com.techdb.app.rxfluxarchitecture.action.Keys;
import com.techdb.app.rxfluxarchitecture.models.GitHubRepo;

import java.util.ArrayList;

/**
 * Created by HoaLQ on 11/23/15.
 */
public class RepositoriesStore extends RxStore implements RepositoriesStoreInterface {
    public static final String REPOSITORIES_ID = "Repositories";
    private static RepositoriesStore mInstance;
    private ArrayList<GitHubRepo> mGitHubRepos;

    public RepositoriesStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static synchronized RepositoriesStore getInstance(Dispatcher dispatcher) {
        if (mInstance == null) {
            mInstance = new RepositoriesStore(dispatcher);
        }
        return mInstance;
    }

    @Override
    public ArrayList<GitHubRepo> getRepositories() {
        return mGitHubRepos == null ? new ArrayList<>() : mGitHubRepos;
    }

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.GET_REPOSITORIES:
                this.mGitHubRepos = (ArrayList<GitHubRepo>) action.getData().get(Keys.REPOSITORY);
                break;
            default:
                break;
        }
        postChange(new RxStoreChange(REPOSITORIES_ID, action));
    }
}
