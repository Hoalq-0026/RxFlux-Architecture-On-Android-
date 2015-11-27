package com.techdb.app.rxfluxarchitecture;

import android.app.Application;
import android.content.Context;

import com.techdb.app.rxflux.RxFlux;
import com.techdb.app.rxfluxarchitecture.action.GitHubActionCreator;

/**
 * Created by HoaLQ on 11/23/15.
 */
public class GitHubApp extends Application {
    private RxFlux mRxFlux;

    private GitHubActionCreator mActionCreator;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mRxFlux = RxFlux.init(this);
        mActionCreator = new GitHubActionCreator(mRxFlux.getDispatcher(), mRxFlux.getSubscriptionManager());
        mContext = this.getApplicationContext();
    }

    public RxFlux getRxFlux() {
        return this.mRxFlux;
    }

    public void setRxFlux(RxFlux rxFlux) {
        this.mRxFlux = rxFlux;
    }

    public GitHubActionCreator getActionCreator() {
        return mActionCreator;
    }

    public void setActionCreator(GitHubActionCreator actionCreator) {
        this.mActionCreator = actionCreator;
    }

    public static GitHubApp get(Context context) {
        return (GitHubApp) context.getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
