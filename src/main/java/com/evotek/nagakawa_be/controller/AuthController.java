package com.evotek.nagakawa_be.controller;



import com.evotek.nagakawa_be.model.User;
import com.evotek.nagakawa_be.payload.JwtResponse;
import com.evotek.nagakawa_be.payload.LoginRequest;
import com.evotek.nagakawa_be.payload.MessageResponse;
import com.evotek.nagakawa_be.payload.SignupRequest;
import com.evotek.nagakawa_be.repository.RoleRepository;
import com.evotek.nagakawa_be.repository.UserRepository;
import com.evotek.nagakawa_be.service.UserDetailImpl;
import com.evotek.nagakawa_be.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
        System.out.println("before loginnnnnnnnnnn");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        System.out.println("loginnnnnnnnnnnnnn");
        System.err.println(loginRequest.getEmail());
        System.err.println(loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetail.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetail.getId(),
                userDetail.getUsername(),
                roles
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest){
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getFirstname(), signupRequest.getLastname());

//        Set<String> strRole = signupRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if(strRole == null){
//            Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER)
//                    .orElseThrow(()->new RuntimeException("Error: Role is  not found"));
//            roles.add(userRole);
//        }
//        else{
//            strRole.forEach(role->{
//                if(role.equals("admin")){
//                    Role adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
//                            .orElseThrow(()->new RuntimeException("Error: Role is  not found"));
//                    roles.add(adminRole);
//                }
//                else{   // role.equals("user")
//                    Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER)
//                            .orElseThrow(()->new RuntimeException("Error: Role is  not found"));
//                    roles.add(userRole);
//                }
////                switch (role){
////                    case "admin":
////                        Role adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
////                                .orElseThrow(()->new RuntimeException("Error: Role is  not found"));
////                        roles.add(adminRole);
////                        break;
////                    case "user":
////                        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER)
////                                .orElseThrow(()->new RuntimeException("Error: Role is  not found"));
////                        roles.add(userRole);
////                        break;
////                    default:
////                        Role userRoled = roleRepository.findByRoleName(RoleName.ROLE_USER)
////                                .orElseThrow(()->new RuntimeException("Error: Role is  not found"));
////                        roles.add(userRoled);
////                }
//            });
//        }
//        user.setRoles(roles);
//
//        Long deptId = signupRequest.getDeptId();
//        Department department = null;
//        if(deptId != null){
//            department = departmentRepository.findById(deptId)
//                    .orElseThrow(()->new RuntimeException("Error: Department is  not found"));
//
//        }
//        user.setDepartment(department);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User register successfully!"));
    }
}
