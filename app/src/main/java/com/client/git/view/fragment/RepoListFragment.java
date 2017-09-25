package com.client.git.view.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.client.git.R;
import com.client.git.model.vo.Organization;
import com.client.git.model.vo.Repository;
import com.client.git.presenter.BasePresenter;
import com.client.git.presenter.RepoPresenter;
import com.client.git.util.Util;
import com.client.git.view.adapter.RepoAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class RepoListFragment extends BaseFragment implements RepoListView{

    private static String TAG_ORG = "TAG_ORG";
    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Bind(R.id.repo_recycler)
    RecyclerView repoRecyclerView;

    @Bind(R.id.tv_count_repo)
    TextView tvCountRepo;

    private RepoPresenter presenter;
    private String nameOrg;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    public static RepoListFragment newInstance(Organization organization) {
        RepoListFragment myFragment = new RepoListFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, organization);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.repo_list, container, false);
        ButterKnife.bind(this, view);

//        String infoText = getRepositoryVO().getRepoName() + " (" + getRepositoryVO().getOwnerName() + ")";
//        info.setText(infoText);

        repoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Organization organization = getOrganizationVO();
        nameOrg = organization.getName();
        presenter = new RepoPresenter(this, organization);
        presenter.onCreate(savedInstanceState);

        return view;
    }

    @OnClick(R.id.img_but_back)
    public void onClick() {
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_ORG);
        if (fragment == null)
        fragment = new OrgListFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG_ORG);
        transaction.commit();
    }

    private Organization getOrganizationVO() {
        return (Organization) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(presenter!=null) presenter.onSaveInstanceState(outState);

    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    private void makeToast(String text) {
        Snackbar.make(repoRecyclerView, text, Snackbar.LENGTH_LONG).show();
    }
    @Override
    public void showRepoList(List<Repository> repositoryList) {
        repoRecyclerView.setAdapter(new RepoAdapter(getContext(), repositoryList, presenter));
    }

    @Override
    public void showCountRepo(Integer countRepo){
        tvCountRepo.setText(nameOrg + " Repositories (" + countRepo + ")");
    }
}
