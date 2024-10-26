package com.st.project_manager.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.Entity.UserPerson;

@Repository
public interface UserPersonRepository extends CrudRepository<UserPerson, Long> {
	Iterable<UserPerson> findAll();
}
