package com.client.git.presenter;

import com.client.git.model.Model;
import com.client.git.model.ModelImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by TechnoA on 22.09.2017.
 */

public abstract class BasePresenter implements Presenter {

    protected Model dataGitHub = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

}
