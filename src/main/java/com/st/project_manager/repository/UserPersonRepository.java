package com.st.project_manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.Entity.UserPerson;

@Repository
public interface UserPersonRepository extends JpaRepository<UserPerson, Integer> {
}
