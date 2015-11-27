package com.techdb.app.rxfluxarchitecture.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techdb.app.rxfluxarchitecture.R;
import com.techdb.app.rxfluxarchitecture.models.GitHubRepo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HoaLQ on 11/23/15.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    private ArrayList<GitHubRepo> repos;
    private OnRepoClicked callback;

    public RepoAdapter() {
        super();
        repos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GitHubRepo repo = repos.get(position);
        holder.bindData(repo);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null){
                callback.onClicked(repo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setRepos(ArrayList<GitHubRepo> repos){
        this.repos = repos;
        notifyDataSetChanged();
    }

    public void setCallback(OnRepoClicked callback){
        this.callback = callback;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView nameView;
        @Bind(R.id.description)
        TextView descView;
        @Bind(R.id.id)
        TextView idView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(GitHubRepo repo) {
            nameView.setText(repo.getName());
            descView.setText(repo.getDescription());
            idView.setText("Github id: " + repo.getId());
        }
    }

    public interface OnRepoClicked {
        void onClicked(GitHubRepo repo);
    }
}
