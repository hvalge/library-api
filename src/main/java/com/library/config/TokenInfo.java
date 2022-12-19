package com.library.config;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@AllArgsConstructor
public class TokenInfo {

    String userName;
    List<String> roles;

    public TokenInfo(String userName, String roles) {
        this.userName = userName;
        this.roles = List.of(roles.split(", "));
    }

    public String getRolesAsString() {
        return String.join(", ", roles);
    }

}
