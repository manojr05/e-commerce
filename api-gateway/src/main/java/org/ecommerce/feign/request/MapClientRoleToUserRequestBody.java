package org.ecommerce.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class MapClientRoleToUserRequestBody {

    List<Role> roles;

    @Data
    static class Role {
        private String id;
        private String name;
        private String description;
        private boolean composite;
        @JsonProperty("clientRole")
        private boolean isClientRole;
        private String containerId;
        private Attributes attributes;

        @Data
        static class Attributes {
        }
    }
}
