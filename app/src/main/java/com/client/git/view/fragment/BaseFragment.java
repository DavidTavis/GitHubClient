package com.client.git.view.fragment;

import android.support.v4.app.Fragment;

import com.client.git.presenter.BasePresenter;

/**
 * Created by TechnoA on 22.09.2017.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}

