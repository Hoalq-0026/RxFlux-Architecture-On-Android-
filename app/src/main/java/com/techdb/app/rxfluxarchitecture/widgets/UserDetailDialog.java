package com.techdb.app.rxfluxarchitecture.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techdb.app.rxfluxarchitecture.GitHubApp;
import com.techdb.app.rxfluxarchitecture.R;
import com.techdb.app.rxfluxarchitecture.models.GitUser;
import com.techdb.app.rxfluxarchitecture.stores.UsersStore;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HoaLQ on 11/24/15.
 */
public class UserDetailDialog extends DialogFragment {
    private static final String ARGUMENT_USER_ID = "user_id";
    private String mUserId;

    @Bind(R.id.user_name)
    TextView userNameView;
    @Bind(R.id.login)
    TextView loginView;
    @Bind(R.id.statistics)
    TextView statsView;

    public static UserDetailDialog newInstance(String userId) {
        UserDetailDialog userDialog = new UserDetailDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_USER_ID, userId);
        userDialog.setArguments(bundle);
        return userDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Material_Dialog);
        if (getArguments() != null) {
            mUserId = getArguments().getString(ARGUMENT_USER_ID);
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        view.setOnClickListener(view1 -> {
        });
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GitUser user = UsersStore.getInstance(GitHubApp.get(getActivity()).getRxFlux().getDispatcher()).getUser(mUserId);
        userNameView.setText(user.getName());
        loginView.setText(user.getLogin());
        statsView.setText(getStats(user));
    }

    private String getStats(GitUser user) {
        StringBuilder stats = new StringBuilder();
        stats.append("Public repos: ").append(user.getPublicRepos()).append("\n");
        stats.append("Public gists: ").append(user.getPublicGists()).append("\n");
        stats.append("Followers: ").append(user.getFollowers());
        return stats.toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
