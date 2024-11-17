package com.st.project_manager.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.st.project_manager.auth.AuthResponse;
import com.st.project_manager.auth.LoginRequest;
import com.st.project_manager.auth.RegisterRequest;
import com.st.project_manager.entity.Role;
import com.st.project_manager.entity.UserPerson;
import com.st.project_manager.entity.UserPersonHasRole;
import com.st.project_manager.repository.UserPersonRepository;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserPersonRepository userPersonRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthServiceImpl(UserPersonRepository userPersonRepository, JwtService jwtService,
      PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.userPersonRepository = userPersonRepository;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public AuthResponse login(LoginRequest req) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
    UserDetails user = userPersonRepository.findByUserName(req.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);
    AuthResponse response = new AuthResponse();
    response.setToken(token);
    return response;
  }

  @Override
  public AuthResponse register(RegisterRequest req) {
    Role defaultRole = new Role();
    defaultRole.setCode("USER");
    defaultRole.setName("USER");

    UserPersonHasRole userPersonHasRole = new UserPersonHasRole();
    userPersonHasRole.setRole(defaultRole);

    AuthResponse response = new AuthResponse();

    UserPerson newUser = new UserPerson();
    newUser.setUserName(req.getUsername());
    newUser.setPassword(passwordEncoder.encode(req.getPassword()));
    newUser.setFirstName(req.getFirstName());
    newUser.setLastName(req.getLastName());
    newUser.setRole(List.of(userPersonHasRole));

    userPersonRepository.save(newUser);

    response.setToken(jwtService.getToken(newUser));
    return response;
  }

}
