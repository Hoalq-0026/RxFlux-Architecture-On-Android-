package com.techdb.app.rxflux.store;

import com.techdb.app.rxflux.action.RxAction;

/**
 * Created by HoaLQ on 11/23/15.
 */
public class RxStoreChange {
    public String mStoreId;
    public RxAction mRxAction;

    public RxStoreChange(String storeId, RxAction rxAction) {
        this.mStoreId = storeId;
        this.mRxAction = rxAction;
    }

    public RxAction getRxAction() {
        return this.mRxAction;
    }

    public String getStoreId() {
        return this.mStoreId;
    }
}
