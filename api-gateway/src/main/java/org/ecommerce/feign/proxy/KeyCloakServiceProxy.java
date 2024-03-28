package org.ecommerce.feign.proxy;

import feign.Headers;
import org.ecommerce.config.FormFeignEncoderConfig;
import org.ecommerce.feign.request.MapRoleRequestBody;
import org.ecommerce.feign.request.RegisterUserFeignRequestDTO;
import org.ecommerce.feign.request.TokenRequestForm;
import org.ecommerce.feign.response.KeyCloakTokenResponse;
import org.ecommerce.feign.response.KeycloakUserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "keycloak-service", url = "http://localhost:9090", configuration = FormFeignEncoderConfig.class)
public interface KeyCloakServiceProxy {

    @PostMapping(value = "/realms/e-commerce/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<KeyCloakTokenResponse> getToken(@RequestBody TokenRequestForm tokenRequestForm);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/admin/realms/{realm}/users", consumes = "application/json")
    void registerUser(@RequestHeader("Authorization") String bearer,
                      @RequestBody RegisterUserFeignRequestDTO registerUserRequestDTO,
                      @PathVariable("realm") String realm);


    @GetMapping("/admin/realms/{realm}/users")
    ResponseEntity<List<KeycloakUserDetails>> getUserByUsername(@RequestHeader("Authorization") String authorization,
                                           @RequestParam("username") String username,
                                           @PathVariable("realm") String realm);

    @PostMapping(value = "/admin/realms/{realm}/users/{userId}/role-mappings/clients/{clientId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void assignUserRoleToUser(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody List<MapRoleRequestBody> requestBody,
            @PathVariable("realm") String realm,
            @PathVariable("userId") String userId,
            @PathVariable("clientId") String clientId
    );


}
