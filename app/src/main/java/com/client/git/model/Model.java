package com.client.git.model;

import com.client.git.model.dto.org.OrganizationDTO;
import com.client.git.model.dto.repo.RepositoryDTO;

import java.util.List;

import rx.Observable;

/**
 * Created by TechnoA on 22.09.2017.
 */

public interface Model {

    Observable<List<RepositoryDTO>> getRepositories(String org);

    Observable<OrganizationDTO> getSearchOrganization(String org);

}
