package com.syed.userserviceapi.dto;

import com.syed.userserviceapi.enums.AuthStatus;

public record AuthResponseDto(String token, AuthStatus authStatus) {
}
