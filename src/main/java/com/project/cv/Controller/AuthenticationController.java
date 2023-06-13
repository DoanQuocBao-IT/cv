package com.project.cv.Controller;

import com.project.cv.Dto.RegisterDto;
import com.project.cv.JWT.JwtRequest;
import com.project.cv.JWT.JwtResponse;
import com.project.cv.JWT.JwtTokenProvider;
import com.project.cv.JWT.UserDetailServiceImpl;
import com.project.cv.Service.PasswordResetService;
import com.project.cv.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    PasswordResetService passwordResetService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new IllegalArgumentException("Incorrect username or password");
        }
        final UserDetails userDetails= userDetailService.loadUserByUsername(jwtRequest.getUsername());
        final String jwt = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody RegisterDto registerDto) throws Exception{
        boolean isUserCreated=userService.addRegisterUser(registerDto);
        if (isUserCreated){
            UserDetails userDetails= userDetailService.loadUserByUsername(registerDto.getUsername());
            String jwt = jwtTokenProvider.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(jwt));
        }else {
            throw new IllegalArgumentException("Invalid passcode");
        }
    }
    @PostMapping("/change-password")
    public void changePassword(@RequestBody String password){
        userService.changePassword(password);
    }
    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody String email) {
        passwordResetService.sendPasswordResetMail(email);
    }
    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String passcode,@RequestBody String password){
        passwordResetService.resetPassword(passcode, password);
    }

}
