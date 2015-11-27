package com.techdb.app.rxflux.dispatcher;

import com.techdb.app.rxflux.action.RxError;
import com.techdb.app.rxflux.store.RxStoreChange;

/**
 * Created by HoaLQ on 11/23/15.
 */
public interface RxViewDispatch {

    /**
     * All the stores will call this event so the view an react and request the needed data.
     *
     * @param change the changed signals for store
     */
    void onRxStoreChanged(RxStoreChange change);

    void onRxError(RxError error);

    /**
     * When an activity has been registered RxFlux wil notify the activity so it can register
     * custom views of fragments.
     */
    void onRxViewRegistered();

    /**
     * When the activity goes to Pause RxFlux wll unregister it and the call this method so the
     * activity can unregister custom views of fragments.
     */
    void onRxViewUnregistered();

    /**
     * RxFlux method to let the view create the stores that need for this activity, this method is called
     * every time the activity si created. Normally you will instantiate the store with singleton instance.
     */
    void onRxStoresRegister();
}
