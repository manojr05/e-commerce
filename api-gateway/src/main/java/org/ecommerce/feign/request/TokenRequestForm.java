package org.ecommerce.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenRequestForm {
    @JsonProperty("grant_type")
    private String grantType;
    @JsonProperty("client_id")
    private String clientId;
    private String username;
    private String password;
    @JsonProperty("client_secret")
    private String clientSecret;
}
