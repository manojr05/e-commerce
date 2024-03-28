package org.ecommerce.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeyCloakTokenResponse {
    @JsonProperty("access_token")
    String accessToken;
}
