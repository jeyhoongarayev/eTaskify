package com.abb.etaskify.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abb.etaskify.db.entity.Organization;
import com.abb.etaskify.db.repository.OrganizationRepository;
import com.abb.etaskify.db.repository.UserRepository;
import com.abb.etaskify.mapper.OrganizationMapper;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.OrganizationRequest;
import com.abb.etaskify.model.response.OrganizationResponse;
import com.abb.etaskify.security.PasswordConfig;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrganizationServiceImpl.class, PasswordConfig.class})
@ExtendWith(SpringExtension.class)
class OrganizationServiceImplTest {

    public static final String ADDRESS = "BAKU";
    public static final String ID = "1";
    public static final String ORGANIZATION_NAME = "DEMO";
    public static final String PHONE_NUMBER = "1234567890";
    public static final String USERNAME = "user";
    public static final String EMAIL = "random@gmail.com";
    public static final String PASSWORD = "123456";
    public static final String ROLE = "ADMIN";
    public static final String ORGANIZATION_ID = "2";

    @MockBean
    private OrganizationMapper organizationMapper;

    @MockBean
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testRegister() {
        Organization organization = new Organization();

        Organization organization1 = new Organization();
        when(this.organizationRepository.findByOrganizationNameAndIsActive((String) any(), (Boolean) any()))
                .thenReturn(organization);
        when(this.organizationRepository.save((Organization) any())).thenReturn(organization1);
        ResponseData<OrganizationResponse> actualRegisterResult = this.organizationServiceImpl
                .register(new OrganizationRequest(USERNAME, ORGANIZATION_NAME, PHONE_NUMBER, ADDRESS,
                        EMAIL, PASSWORD, ROLE));
        assertEquals(409, actualRegisterResult.getCode());
        assertEquals("ALREADY EXIST", actualRegisterResult.getMessage());
        assertNull(actualRegisterResult.getData());
        verify(this.organizationRepository).findByOrganizationNameAndIsActive((String) any(), (Boolean) any());
    }

    @Test
    void testUpdate() {
        Organization organization = new Organization();
        Optional<Organization> ofResult = Optional.of(organization);

        Organization organization1 = new Organization();

        when(this.organizationRepository.save((Organization) any())).thenReturn(organization1);
        when(this.organizationRepository.findByIdAndIsActive((String) any(), (Boolean) any())).thenReturn(ofResult);

        Organization organization2 = new Organization();
        OrganizationResponse organizationResponse = new OrganizationResponse(USERNAME, ORGANIZATION_NAME, PHONE_NUMBER, ADDRESS,
                EMAIL, PASSWORD, ROLE);

        when(this.organizationMapper.toOrganizationResponse((Organization) any())).thenReturn(organizationResponse);
        when(this.organizationMapper.toOrganization((OrganizationRequest) any())).thenReturn(organization2);
        ResponseData<OrganizationResponse> actualUpdateResult = this.organizationServiceImpl.update(new OrganizationRequest(
                USERNAME, ORGANIZATION_NAME, PHONE_NUMBER, ADDRESS,
                EMAIL, PASSWORD, ROLE), ORGANIZATION_ID);
        assertEquals(HttpStatus.CREATED.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.CREATED.name(), actualUpdateResult.getMessage());
        assertSame(organizationResponse, actualUpdateResult.getData());
        verify(this.organizationRepository).save((Organization) any());
        verify(this.organizationRepository).findByIdAndIsActive((String) any(), (Boolean) any());
        verify(this.organizationMapper).toOrganization((OrganizationRequest) any());
        verify(this.organizationMapper).toOrganizationResponse((Organization) any());
    }

    @Test
    void testUpdate2() {
        Organization organization = new Organization();

        when(this.organizationRepository.save((Organization) any())).thenReturn(organization);
        when(this.organizationRepository.findByIdAndIsActive((String) any(), (Boolean) any())).thenReturn(Optional.empty());

        Organization organization1 = new Organization();

        when(this.organizationMapper.toOrganizationResponse((Organization) any())).thenReturn(new OrganizationResponse(
                USERNAME, ORGANIZATION_NAME, PHONE_NUMBER, ADDRESS, EMAIL, PASSWORD, ROLE));
        when(this.organizationMapper.toOrganization((OrganizationRequest) any())).thenReturn(organization1);
        ResponseData<OrganizationResponse> actualUpdateResult = this.organizationServiceImpl.update(new OrganizationRequest(
                USERNAME, ORGANIZATION_NAME, PHONE_NUMBER, ADDRESS, EMAIL, PASSWORD, ROLE), ORGANIZATION_ID);
        assertEquals(HttpStatus.NOT_FOUND.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), actualUpdateResult.getMessage());
        assertNull(actualUpdateResult.getData());
        verify(this.organizationRepository).findByIdAndIsActive((String) any(), (Boolean) any());
    }
}

