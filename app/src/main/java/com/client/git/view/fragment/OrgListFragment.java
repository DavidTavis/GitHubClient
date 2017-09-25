package com.client.git.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.client.git.R;
import com.client.git.model.vo.Organization;
import com.client.git.presenter.BasePresenter;
import com.client.git.presenter.OrgPresenter;
import com.client.git.util.Util;
import com.client.git.view.ActivityCallback;
import com.client.git.view.adapter.OrgAdapter;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class OrgListFragment extends BaseFragment implements OrgListView {

    @Bind(R.id.org_recycler)
    RecyclerView recyclerView;

    @Bind(R.id.et_organization)
    EditText inputOrg;

    @Bind(R.id.prgLoading)
    CircleProgressBar mPrgLoading;

    private OrgAdapter adapter;

    private OrgPresenter presenter = new OrgPresenter(this);

    private ActivityCallback activityCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        android.view.View view = inflater.inflate(R.layout.org_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new OrgAdapter(getContext(), new ArrayList<>(), presenter);
        recyclerView.setAdapter(adapter);

        Subscription editTextSub = RxTextView.textChanges(inputOrg).filter(s -> s.length() > 2)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(value -> presenter.onSearchOrganization(value.toString()));

        presenter.onCreate(savedInstanceState);

        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String error) {
        makeToast(error);
        Util.LogInfo(error);
    }

    @Override
    public void showOrgList(List<Organization> organizationList) {
        adapter.setOrgList(organizationList);
    }

    @Override
    public void startRepoListFragment(Organization organization) {
        activityCallback.startRepoListFragment(organization);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    public void showProgressBar(){
        mPrgLoading.setColorSchemeResources(R.color.colorAccent);
        mPrgLoading.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgressBar(){
        mPrgLoading.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(presenter!= null) presenter.onSaveInstanceState(outState);

    }

}
