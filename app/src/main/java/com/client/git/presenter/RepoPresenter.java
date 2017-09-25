package com.client.git.presenter;

import android.os.Bundle;

import com.client.git.model.dto.repo.RepositoryDTO;
import com.client.git.model.vo.Organization;
import com.client.git.model.vo.Repository;
import com.client.git.presenter.mapper.RepoMapper;
import com.client.git.view.fragment.RepoListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by TechnoA on 24.09.2017.
 */

public class RepoPresenter extends BasePresenter {

    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    private RepoListView view;

    private RepoMapper repoMapper = new RepoMapper();
    private List<Repository> repositoryList = new ArrayList<>();

    private Organization organization;

    public RepoPresenter(RepoListView view, Organization organization) {
        this.view = view;
        this.organization = organization;
    }

    public void loadRepositoryData(){
        String name = organization.getName();

        Subscription subscription = dataGitHub.getRepositories(name)
                .map(repoMapper).subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Repository> list) {
                        if (list != null && !list.isEmpty()) {
                            repositoryList = list;
                            view.showRepoList(list);
                            view.showCountRepo(list.size());
                        } else {
//                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            repositoryList = (List<Repository>) savedInstanceState.getSerializable(BUNDLE_REPO_KEY);
        }

        if(isRepoListEmpty()) {
            loadRepositoryData();
        }else {
            view.showRepoList(repositoryList);
            view.showCountRepo(repositoryList.size());
        }
    }

    private boolean isRepoListEmpty() {
        return repositoryList == null || repositoryList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isRepoListEmpty()) {
            outState.putSerializable(BUNDLE_REPO_KEY, new ArrayList<>(repositoryList));
        }
    }
}
