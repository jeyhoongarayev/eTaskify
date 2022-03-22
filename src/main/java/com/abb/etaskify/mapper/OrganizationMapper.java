package com.abb.etaskify.mapper;

import com.abb.etaskify.db.entity.Organization;
import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.model.request.OrganizationRequest;
import com.abb.etaskify.model.response.OrganizationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OrganizationMapper {

    Organization toOrganization(OrganizationRequest organizationRequest);

    OrganizationResponse toOrganizationResponse(Organization organization);

}
