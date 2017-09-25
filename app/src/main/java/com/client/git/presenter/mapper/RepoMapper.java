package com.client.git.presenter.mapper;

import com.client.git.model.dto.repo.RepositoryDTO;
import com.client.git.model.vo.Repository;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by TechnoA on 24.09.2017.
 */

public class RepoMapper implements Func1<List<RepositoryDTO>, List<Repository>> {
    @Override
    public List<Repository> call(List<RepositoryDTO> repositoryDTOs) {
        List<Repository> repoList = Observable.from(repositoryDTOs)
                .map(repoDTO -> new Repository(repoDTO.getName(), repoDTO.description))
                .toList()
                .toBlocking()
                .first();
        return repoList;
    }
}