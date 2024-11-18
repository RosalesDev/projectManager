package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import com.st.project_manager.auth.AuthResponse;
import com.st.project_manager.dto.UserPersonDTO;

public interface UserPersonService {

    AuthResponse createUserPerson(UserPersonDTO userPerson);

    List<UserPersonDTO> getAllUserPerson();

    Optional<UserPersonDTO> getUserPersonById(Integer id);

    UserPersonDTO updateUserPerson(Integer id, UserPersonDTO userPerson);

    Optional<UserPersonDTO> deleteUserPerson(Integer id);

}
