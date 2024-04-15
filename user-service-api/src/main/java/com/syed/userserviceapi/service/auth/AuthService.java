package com.syed.userserviceapi.service.auth;

public interface AuthService {
    String login(String username, String password);

    String signup(String name, String username, String password);
}
