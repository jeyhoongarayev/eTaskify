package com.abb.etaskify.services.impl;

import com.abb.etaskify.db.entity.Organization;
import com.abb.etaskify.db.entity.User;
import com.abb.etaskify.db.repository.OrganizationRepository;
import com.abb.etaskify.db.repository.UserRepository;
import com.abb.etaskify.mapper.OrganizationMapper;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.OrganizationRequest;
import com.abb.etaskify.model.response.OrganizationResponse;
import com.abb.etaskify.security.PasswordConfig;
import com.abb.etaskify.services.OrganizationService;
import com.abb.etaskify.util.GenerateResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final PasswordConfig passwordConfig;
    private final OrganizationMapper organizationMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseData<OrganizationResponse> register(OrganizationRequest organizationRequest) {
        Organization organizationName = organizationRepository.findByOrganizationNameAndIsActive(organizationRequest.getOrganizationName(), true);
        if (organizationName != null){
            return ResponseData.<OrganizationResponse>builder()
                    .code(409)
                    .message("ALREADY EXIST")
                    .data(null)
                    .build();
        }

        Organization saveOrganization = organizationRepository.save(organizationMapper.toOrganization(organizationRequest));
        User user = User.builder()
                .username(organizationRequest.getUsername())
                .email(organizationRequest.getEmail())
                .password(organizationRequest.getPassword())
                .role("ADMIN")
                .idOrganization(saveOrganization.getId())
                .build();
        userRepository.save(user);
        return GenerateResponseUtility.orgFunc.generate(200,"SUCCESS", organizationMapper.toOrganizationResponse(saveOrganization));
    }

    @Override
    public ResponseData<OrganizationResponse> update(OrganizationRequest organizationRequest, String organizationId) {
        Optional<Organization> optionalOrganization = organizationRepository.findByIdAndIsActive(organizationId, true);
        if (optionalOrganization.isPresent()){
            Organization organization = organizationMapper.toOrganization(organizationRequest);
            organization.setId(organizationId);
            Organization savedOrganization = organizationRepository.save(organization);
            return GenerateResponseUtility.orgFunc.generate(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), organizationMapper.toOrganizationResponse(savedOrganization));
        }else{
            return GenerateResponseUtility.orgFunc.generate(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
    }

}
