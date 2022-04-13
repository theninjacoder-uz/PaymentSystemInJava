package com.example.paymentsysteminjava.controller.auth;

import com.example.paymentsysteminjava.dto.UserLoginDto;
import com.example.paymentsysteminjava.dto.UserRegisterDto;
import com.example.paymentsysteminjava.dto.response.ApiJwtResponse;
import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.repository.UserRepository;
import com.example.paymentsysteminjava.service.jwt.JwtProvider;
import com.example.paymentsysteminjava.service.user.UserService;
import com.example.paymentsysteminjava.service.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImp userServiceImp;


    @PostMapping("/agent_login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        UserEntity agentEntity =  userService.login(userLoginDto, userRepository, passwordEncoder);
        if(agentEntity == null)
            throw new UsernameNotFoundException("User not found");

        String accessToken = jwtProvider.generateAccessToken(agentEntity);
        String refreshToken = jwtProvider.generateRefreshToken(agentEntity);

        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, refreshToken));
    }

    @PostMapping("/user_login")
    public ResponseEntity<?> getToken(@RequestBody UserLoginDto userLoginDto) {
        UserEntity userEntity =  userService.login(userLoginDto,userRepository,passwordEncoder);
        String accessToken = jwtProvider.generateAccessToken(userEntity);
        String refreshToken = jwtProvider.generateRefreshToken(userEntity);

        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, refreshToken));
    }

//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/refresh_token")
    public ResponseEntity<?> getRefreshToken(@RequestBody String token) {
        String accessToken = jwtProvider.getAccessTokenFromRefreshToken(token);
        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, token));
    }

    @PostMapping("/add_admin")
    public ResponseEntity<Boolean> addSuperAdmin(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userServiceImp.addSuperAdmin(userRegisterDto));
    }


}
