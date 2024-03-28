package org.ecommerce.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserRequestDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}






