package com.client.git.view.fragment;

import com.client.git.model.vo.Repository;

import java.util.List;

/**
 * Created by TechnoA on 24.09.2017.
 */

public interface RepoListView extends View {

    void showRepoList(List<Repository> vo);

    void showEmptyList();

    void showCountRepo(Integer count);

}
