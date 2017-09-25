package com.client.git.model;

import com.client.git.model.api.Interface;
import com.client.git.model.api.Module;
import com.client.git.model.dto.org.OrganizationDTO;
import com.client.git.model.dto.repo.RepositoryDTO;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private Interface apiInterface = Module.getInterface();

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
        ;
    }

    @Override
    public Observable<List<RepositoryDTO>> getRepositories(String org) {
        return apiInterface
                .getRepositories(org)
                .compose(applySchedulers());
    }

    @Override
    public Observable<OrganizationDTO> getSearchOrganization(String org) {
        return apiInterface
                .getSearchOrganization(org)
                .compose(applySchedulers());
    }


    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}