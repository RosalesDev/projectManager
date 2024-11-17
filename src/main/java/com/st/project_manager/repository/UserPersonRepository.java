package com.st.project_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.UserPerson;

@Repository
public interface UserPersonRepository extends JpaRepository<UserPerson, Integer> {
  Optional<UserPerson> findByUserName(String userName);
}
