package org.ecommerce.feign.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class RegisterUserFeignRequestDTO {

        private boolean enabled;
        private Attributes attributes;
        private ArrayList<Object> groups;
        private String email;
        private boolean emailVerified;
        private String firstName;
        private String lastName;
        private String username;
        private ArrayList<Credential> credentials;

        @Data
        @NoArgsConstructor
        public static class Attributes {
        }

        @Data
        @NoArgsConstructor
        public static class Credential {
            private String type;
            private String value;
            private boolean temporary;
        }
}
