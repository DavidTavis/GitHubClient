package com.client.git.model.api;

import com.client.git.model.dto.org.OrganizationDTO;
import com.client.git.model.dto.repo.RepositoryDTO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by TechnoA on 22.09.2017.
 */

public interface Interface {

    @GET("/users/{user}/repos")
    Observable<List<RepositoryDTO>> getRepositories(@Path("user") String organization);

    @GET("/search/users")
    Observable<OrganizationDTO> getSearchOrganization(@Query("q") String organization);

}
