package com.client.git.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.client.git.R;
import com.client.git.model.vo.Repository;
import com.client.git.presenter.RepoPresenter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TechnoA on 23.09.2017.
 */

public class RepoAdapter extends UltimateViewAdapter<RecyclerView.ViewHolder> {

    private RepoPresenter presenter;
    private Picasso mPicasso;
    private List<Repository> repositoryList;
    private Interpolator interpolator = new LinearInterpolator();
    private int lastPosition = 5;
    private final int ANIMATION_DURATION = 300;

    public RepoAdapter(Context context, List<Repository> list, RepoPresenter presenter) {
        this.repositoryList = list;
        this.mPicasso = Picasso.with(context);
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new UltimateRecyclerviewViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return repositoryList.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position < getItemCount()
                && (customHeaderView != null ? position <= repositoryList.size() : position < repositoryList.size())
                && (customHeaderView == null || position > 0)) {
            Repository repository;
            repository = repositoryList.get(customHeaderView != null ? position - 1 : position);
            // Set data to the view
            ((ViewHolder) holder).textName.setText(repository.getName());
            ((ViewHolder) holder).textDescription.setText(repository.getDescription());

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public static class ViewHolder extends UltimateRecyclerviewViewHolder {
        TextView textName;
        TextView textDescription;

        public ViewHolder(View v) {
            super(v);
            textName = (TextView) itemView.findViewById(R.id.tv_repo_name);
            textDescription= (TextView) itemView.findViewById(R.id.tv_description);
        }
    }

    public void setRepoList(List<Repository> list) {
        this.repositoryList = list;
        notifyDataSetChanged();
    }

}