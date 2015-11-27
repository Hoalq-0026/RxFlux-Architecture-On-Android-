package com.techdb.app.rxfluxarchitecture.stores;

import android.util.ArrayMap;

import com.techdb.app.rxflux.action.RxAction;
import com.techdb.app.rxflux.dispatcher.Dispatcher;
import com.techdb.app.rxflux.store.RxStore;
import com.techdb.app.rxflux.store.RxStoreChange;
import com.techdb.app.rxfluxarchitecture.action.Actions;
import com.techdb.app.rxfluxarchitecture.action.Keys;
import com.techdb.app.rxfluxarchitecture.models.GitUser;

import java.util.ArrayList;

/**
 * Created by HoaLQ on 11/24/15.
 */
public class UsersStore extends RxStore implements UserStoreInterface {
    public static final String USER_STORE_ID = "UsersStore";
    private static UsersStore mInstance;
    private ArrayMap<String, GitUser> mUsersMap;

    public UsersStore(Dispatcher dispatcher) {
        super(dispatcher);
        mUsersMap = new ArrayMap<>();
    }

    public static synchronized UsersStore getInstance(Dispatcher dispatcher) {
        if (mInstance == null) {
            mInstance = new UsersStore(dispatcher);
        }
        return mInstance;
    }

    /**
     * This callback will get all the actions, each store must react on the types he want and do some
     * logic with the model, for example add it to the list to cache it, modify fields etc.. all the
     * logic for the models should go here and then call postChange so the
     * view request the new data.
     *
     * @param action
     */
    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.GET_USER:
                GitUser user = (GitUser) action.getData().get(Keys.User);
                mUsersMap.put(user.getLogin(), user);
                break;
        }

        postChange(new RxStoreChange(USER_STORE_ID, action));
    }

    @Override
    public GitUser getUser(String id) {
        return mUsersMap.get(id);
    }

    @Override
    public ArrayList<GitUser> getUsers() {
        return new ArrayList<>();
    }
}
