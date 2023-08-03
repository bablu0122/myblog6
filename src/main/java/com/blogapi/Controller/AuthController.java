package com.blogapi.Controller;
import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import com.blogapi.payload.JWTAuthResponse;
import com.blogapi.payload.LoginDto;
import com.blogapi.payload.SignUpDto;
import com.blogapi.repository.RoleRepository;
import com.blogapi.repository.UserRepository;
import com.blogapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //http://localhost:8080/api/auth/signup

    //
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Autowired
        private AuthenticationManager authenticationManager;


        //http://localhost:8080/api/auth/signin
        //http://myblog-env.eba-28qhg52w.us-east-1.elasticbeanstalk.com/api/auth/signin
        @PostMapping("/signin")
        public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()));
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // get token form tokenProvider
            String token = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JWTAuthResponse(token));
        }


    //http://myblog-env.eba-28qhg52w.us-east-1.elasticbeanstalk.com/api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new  ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!",HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByMobile(signUpDto.getMobile())) {
            return new ResponseEntity<>("Mobile number  is already taken!",HttpStatus.BAD_REQUEST);
        }

        //create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setMobile(signUpDto.getMobile());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));


        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }
}





