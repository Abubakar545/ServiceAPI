package com.syed.securityservice.controller;


import com.syed.securityservice.enums.AuthStatus;
import com.syed.securityservice.exception.EmailAlreadyExistsException;
import com.syed.securityservice.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.syed.securityservice.dto.AuthRequestDto;
import com.syed.securityservice.dto.AuthResponseDto;
import com.syed.securityservice.service.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto){
        var jwtToken = authService.login(authRequestDto.username(), authRequestDto.password());

        var authReponseDto = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authReponseDto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto){

        try {
            var jwtToken = authService.signup(authRequestDto.name(), authRequestDto.username(), authRequestDto.email(), authRequestDto.password());

            var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authResponseDto);
        } catch(UsernameAlreadyExistsException e) {
            var authResponseDto = new AuthResponseDto(null, AuthStatus.USERNAME_ALREADY_EXISTS);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        } catch(EmailAlreadyExistsException e) {
            var authResponseDto = new AuthResponseDto(null, AuthStatus.EMAIL_ALREADY_EXISTS);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        } catch(Exception e){
            var authResponseDto = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        }
    }

}
