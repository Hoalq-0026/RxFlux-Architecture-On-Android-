package com.techdb.app.rxfluxarchitecture.action;

import com.techdb.app.rxflux.action.RxAction;
import com.techdb.app.rxflux.action.RxActionCreator;
import com.techdb.app.rxflux.dispatcher.Dispatcher;
import com.techdb.app.rxflux.utils.SubscriptionManager;
import com.techdb.app.rxfluxarchitecture.core.GitHubAppService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HoaLQ on 11/23/15.
 */
public class GitHubActionCreator extends RxActionCreator implements Actions {

    public GitHubActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    @Override
    public void getRepositories() {
        final RxAction takeRepositoriesAction = newRxAction(GET_REPOSITORIES);
        if (hasRxAction(takeRepositoriesAction)) {
            return;
        }

        addRxAction(takeRepositoriesAction, GitHubAppService.getRxSampleAppApi()
                .getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos -> postRxAction(newRxAction(GET_REPOSITORIES, Keys.REPOSITORY, repos)),
                        throwable -> postError(takeRepositoriesAction, throwable)));
    }

    @Override
    public void getUserDetail(String userId) {
        final RxAction takeUserAction = newRxAction(GET_USER, Keys.ID, userId);
        if (hasRxAction(takeUserAction)){
            return;
        }

        addRxAction(takeUserAction, GitHubAppService.getRxSampleAppApi().getUser(userId).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user ->{takeUserAction.getData()
                        .put(Keys.User, user);postRxAction(takeUserAction);},
                        throwable -> {postError(takeUserAction,throwable);}));
    }
}
