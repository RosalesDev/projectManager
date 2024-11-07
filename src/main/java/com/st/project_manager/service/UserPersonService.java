package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import com.st.project_manager.dto.UserPersonDTO;

public interface UserPersonService {

    UserPersonDTO createUserPerson(UserPersonDTO userPerson);

    List<UserPersonDTO> getAllUserPerson();

    Optional<UserPersonDTO> getUserPersonById(Integer id);

    UserPersonDTO updateUserPerson(Integer id, UserPersonDTO userPerson);

    Optional<UserPersonDTO> deleteUserPerson(Integer id);

}
