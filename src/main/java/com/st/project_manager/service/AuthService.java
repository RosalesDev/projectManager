package com.st.project_manager.service;

import com.st.project_manager.auth.AuthResponse;
import com.st.project_manager.auth.LoginRequest;
import com.st.project_manager.auth.RegisterRequest;

public interface AuthService {
  public AuthResponse login(LoginRequest req);

  public AuthResponse register(RegisterRequest req);

}
