package org.ecommerce.service;

import org.ecommerce.dto.request.RegisterUserRequestDTO;

public interface GatewayService {
    void registerUser(RegisterUserRequestDTO registerUserDto);
}
