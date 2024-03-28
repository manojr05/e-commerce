package org.ecommerce.feign.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeycloakUserDetails {

        private String id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;

}
