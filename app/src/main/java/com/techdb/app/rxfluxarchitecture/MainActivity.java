package com.techdb.app.rxfluxarchitecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.techdb.app.rxflux.action.RxError;
import com.techdb.app.rxflux.dispatcher.RxViewDispatch;
import com.techdb.app.rxflux.store.RxStoreChange;
import com.techdb.app.rxfluxarchitecture.action.Actions;
import com.techdb.app.rxfluxarchitecture.action.Keys;
import com.techdb.app.rxfluxarchitecture.adapter.RepoAdapter;
import com.techdb.app.rxfluxarchitecture.models.GitHubRepo;
import com.techdb.app.rxfluxarchitecture.stores.RepositoriesStore;
import com.techdb.app.rxfluxarchitecture.stores.UsersStore;
import com.techdb.app.rxfluxarchitecture.widgets.UserDetailDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RxViewDispatch, RepoAdapter.OnRepoClicked {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.loading_frame)
    TextView loadingFrame;

    private RepoAdapter mRepoAdapter;
    private RepositoriesStore mRepositoriesStore;
    private UsersStore mUsersStore;
    private GitHubApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRepoAdapter = new RepoAdapter();
        mRepoAdapter.setCallback(this);
        recyclerView.setAdapter(mRepoAdapter);
        mApp = (GitHubApp) this.getApplication();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mApp.getRxFlux().getDispatcher().unregisterAll();
    }

    @Override
    public void onRxStoreChanged(RxStoreChange change) {
        setLoadingFrame(false);
        switch (change.getStoreId()) {
            case RepositoriesStore.REPOSITORIES_ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_REPOSITORIES:
                        mRepoAdapter.setRepos(mRepositoriesStore.getRepositories());
                        break;
                }
                break;
            case UsersStore.USER_STORE_ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_USER:
                        UserDetailDialog dialog = UserDetailDialog.newInstance((String) change.getRxAction().getData().get(Keys.ID));
                        dialog.show(getSupportFragmentManager(), UserDetailDialog.class.getName());
                }
        }

    }

    @Override
    public void onRxError(RxError error) {
        setLoadingFrame(false);
        if (error.getThrowable() != null) {
            Toast.makeText(this, "Error: " + error.getThrowable().getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnregistered() {

    }

    @Override
    public void onRxStoresRegister() {
        mRepositoriesStore = RepositoriesStore.getInstance(GitHubApp.get(this).getRxFlux().getDispatcher());
        mRepositoriesStore.register();
        mUsersStore = UsersStore.getInstance(GitHubApp.get(this).getRxFlux().getDispatcher());
        mUsersStore.register();
        mApp = GitHubApp.get(MainActivity.this);

    }

    @Override
    public void onClicked(GitHubRepo repo) {
        String userId = repo.getOwner().getLogin();
        if (mUsersStore.getUser(userId) != null) {
            UserDetailDialog dialog = UserDetailDialog.newInstance(userId);
            dialog.show(getSupportFragmentManager(), UserDetailDialog.class.getName());
            return;
        }
        setLoadingFrame(true);
        GitHubApp.get(this).getActionCreator().getUserDetail(userId);
    }

    private void refresh() {
        setLoadingFrame(true);
        mApp.getActionCreator().getRepositories();
    }

    private void setLoadingFrame(boolean show) {
        loadingFrame.setVisibility(show ? View.VISIBLE : View.GONE);

    }

}
