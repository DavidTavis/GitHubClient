package com.client.git.presenter.mapper;

import com.client.git.model.Model;
import com.client.git.model.ModelImpl;
import com.client.git.model.dto.org.Item;
import com.client.git.model.dto.org.OrganizationDTO;
import com.client.git.model.dto.user.UserDTO;
import com.client.git.model.vo.Organization;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class OrgMapper implements Func1<OrganizationDTO, List<Organization>> {

    @Override
    public List<Organization> call(OrganizationDTO organizationDTO) {

        List<Organization> organizationList = Observable.from(organizationDTO.items)
        .map(item -> new Organization(item.login,item.type,item.url,item.avatarUrl))
        .toList()
        .toBlocking()
        .first();

        return organizationList;
    }
}

