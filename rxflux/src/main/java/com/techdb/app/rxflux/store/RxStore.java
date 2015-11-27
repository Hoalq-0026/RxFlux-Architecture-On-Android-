package com.techdb.app.rxflux.store;

import com.techdb.app.rxflux.dispatcher.Dispatcher;
import com.techdb.app.rxflux.dispatcher.RxActionDispatch;

/**
 * Created by HoaLQ on 11/23/15.
 */
public abstract class RxStore implements RxActionDispatch {
    private final Dispatcher mDispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void register() {
        mDispatcher.registerRxAction(this);
    }

    public void postChange(RxStoreChange change) {
        mDispatcher.postRxStoreChange(change);
    }
}
