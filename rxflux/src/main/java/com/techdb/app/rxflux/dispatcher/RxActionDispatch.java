package com.techdb.app.rxflux.dispatcher;

import com.techdb.app.rxflux.action.RxAction;

/**
 * Created by HoaLQ on 11/23/15.
 */
public interface RxActionDispatch {
    void onRxAction(RxAction action);
}
