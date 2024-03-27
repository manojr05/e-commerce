package com.productservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<? extends GrantedAuthority> grantedAuthorities = extractResourceRoles(jwt);
        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        if (jwt.getClaim("resource_access") != null) {
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            Map<String, Object> gatewayRoles = (Map<String, Object>) resourceAccess.get("e-commerce-gateway");
            List<String> eCommerceRoles = (List<String>) gatewayRoles.get("roles");
            List<GrantedAuthority> roles = new ArrayList<>();

            for (String role : eCommerceRoles) {
                roles.add(new SimpleGrantedAuthority("ROLE_"+role));
            }
            return roles;
        }
        return new ArrayList<>();
    }
}
