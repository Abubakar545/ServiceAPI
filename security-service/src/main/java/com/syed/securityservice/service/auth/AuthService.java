package com.syed.securityservice.service.auth;

public interface AuthService {
    String login(String username, String password);

    String signup(String name, String username,  String email, String password);
}
