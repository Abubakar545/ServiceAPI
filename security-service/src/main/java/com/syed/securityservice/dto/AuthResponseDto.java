package com.syed.securityservice.dto;

import com.syed.securityservice.enums.AuthStatus;

public record AuthResponseDto(String token, AuthStatus authStatus) {
}
