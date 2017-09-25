package com.client.git.presenter;

import android.os.Bundle;

import com.client.git.model.vo.Organization;
import com.client.git.presenter.mapper.OrgMapper;
import com.client.git.view.fragment.OrgListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class OrgPresenter extends BasePresenter {

    private static final String BUNDLE_ORG_LIST_KEY = "BUNDLE_ORG_LIST_KEY";
    private static final String BUNDLE_ORG_NAME = "BUNDLE_ORG_NAME";

    private OrgListView view;

    private OrgMapper orgMapper = new OrgMapper();

    private List<Organization> organizationList = new ArrayList<>();

    private String nameOrg = "";

    public OrgPresenter(OrgListView view) {
        this.view = view;
    }

    public void onSearchOrganization(String name) {

        if(nameOrg!=null) {
            if (nameOrg.equals(name)) {
                return;
            } else {
                nameOrg = name;
            }
        }
        Subscription subscription = dataGitHub.getSearchOrganization(name + "type:org")
                .map(orgMapper)
                .subscribe(new Observer<List<Organization>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Organization> list) {

                        if (list != null && !list.isEmpty()) {
                            organizationList = list;
                            view.showOrgList(list);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            organizationList = (List<Organization>) savedInstanceState.getSerializable(BUNDLE_ORG_LIST_KEY);
            nameOrg = savedInstanceState.getString(BUNDLE_ORG_NAME);
        }

        if (!isOrgListEmpty()) {
            view.showOrgList(organizationList);
        }
    }

    private boolean isOrgListEmpty() {
        return organizationList == null || organizationList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isOrgListEmpty()) {
            outState.putSerializable(BUNDLE_ORG_LIST_KEY, new ArrayList<>(organizationList));
            outState.putString(BUNDLE_ORG_NAME, nameOrg);
        }
    }

    public void clickOrganization(Organization organization) {
        onSaveInstanceState(new Bundle());
        view.startRepoListFragment(organization);
    }

}
