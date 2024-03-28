package org.ecommerce.service.impl;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.request.RegisterUserRequestDTO;
import org.ecommerce.feign.proxy.KeyCloakServiceProxy;
import org.ecommerce.feign.request.MapRoleRequestBody;
import org.ecommerce.feign.request.RegisterUserFeignRequestDTO;
import org.ecommerce.feign.response.KeyCloakTokenResponse;
import org.ecommerce.feign.response.KeycloakUserDetails;
import org.ecommerce.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final KeyCloakServiceProxy keyCloakServiceProxy;

    @Value("${keycloak.admin.username}")
    private String adminUsername;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.user-role-uuid}")
    private String userRoleUUID;

    @Value("${keycloak.user-role-name}")
    private String userRoleName;


    @Override
    public void registerUser(RegisterUserRequestDTO registerUserDto) {
        String token;
        ResponseEntity<KeyCloakTokenResponse> tokenResponse = keyCloakServiceProxy.getToken("password", clientId, adminUsername, adminPassword, clientSecret);
        if(tokenResponse.getBody()!=null)
            token=tokenResponse.getBody().getAccessToken();
        else throw new BadRequestException("Error wile retrieving admin token");

        RegisterUserFeignRequestDTO feignRequestDTO = mapRegisterDto(registerUserDto);
        keyCloakServiceProxy.registerUser(token, feignRequestDTO, realm);

        ResponseEntity<List<KeycloakUserDetails>> userByUsernameResponse = keyCloakServiceProxy.getUserByUsername(token, registerUserDto.getUsername(), realm);
        KeycloakUserDetails keycloakUserDetails = Objects.requireNonNull(userByUsernameResponse.getBody()).get(0);

        List<MapRoleRequestBody> list = new ArrayList<>();
        list.add(new MapRoleRequestBody(keycloakUserDetails.getId(), userRoleName));

        keyCloakServiceProxy.assignUserRoleToUser(token, list, realm, userRoleUUID, clientId);
    }

    private RegisterUserFeignRequestDTO mapRegisterDto(RegisterUserRequestDTO registerUserDto){
        RegisterUserFeignRequestDTO requestDTO = new RegisterUserFeignRequestDTO();
        requestDTO.setEnabled(true);
        requestDTO.setUsername(registerUserDto.getUsername());
        requestDTO.setEmail(registerUserDto.getEmail());
        requestDTO.setFirstName(registerUserDto.getFirstName());
        requestDTO.setLastName(registerUserDto.getLastName());

        RegisterUserFeignRequestDTO.Credential credential = new RegisterUserFeignRequestDTO.Credential();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(registerUserDto.getPassword());
        requestDTO.getCredentials().add(credential);

        return requestDTO;
    }
}
