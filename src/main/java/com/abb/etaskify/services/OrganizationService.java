package com.abb.etaskify.services;

import com.abb.etaskify.db.entity.Organization;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.OrganizationRequest;
import com.abb.etaskify.model.response.OrganizationResponse;

public interface OrganizationService {

    ResponseData<OrganizationResponse> register(OrganizationRequest organizationRequest);

    ResponseData<OrganizationResponse> update(OrganizationRequest organizationRequest, String organizationId);

}
