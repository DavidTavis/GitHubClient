package com.client.git.view.fragment;

import com.client.git.model.vo.Organization;
import com.client.git.model.vo.Repository;

import java.util.List;

/**
 * Created by TechnoA on 22.09.2017.
 */

public interface OrgListView extends View {

    void showOrgList(List<Organization> vo);

    void startRepoListFragment(Organization vo);

    void showEmptyList();

}
