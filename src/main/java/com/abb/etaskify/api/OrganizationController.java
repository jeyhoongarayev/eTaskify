package com.abb.etaskify.api;

import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.OrganizationRequest;
import com.abb.etaskify.model.response.OrganizationResponse;
import com.abb.etaskify.services.impl.OrganizationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    private final OrganizationServiceImpl organizationService;

    @PostMapping
    public ResponseEntity<ResponseData<OrganizationResponse>> createOranization(@RequestBody @Valid OrganizationRequest organizationRequest){
        return ResponseEntity.ok(organizationService.register(organizationRequest));
    }

    @PutMapping("/{organizationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseData<OrganizationResponse>> updateOrganization(
            @PathVariable String organizationId,
            @RequestBody OrganizationRequest organizationRequest){
        return ResponseEntity.ok(organizationService.update(organizationRequest, organizationId));
    }
}
