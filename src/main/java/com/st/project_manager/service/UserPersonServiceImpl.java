package com.st.project_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.st.project_manager.Dto.UserPersonDTO;
import com.st.project_manager.Entity.UserPerson;
import com.st.project_manager.mapper.UserPersonMapper;
import com.st.project_manager.repository.UserPersonRepository;

@Service
public class UserPersonServiceImpl implements UserPersonService {
	
	private final UserPersonRepository userPersonRepository;
	
	
//	@Autowired aparentemente ya no es necesario cuando hay solo q constructor y la clase es un bean.
	public UserPersonServiceImpl(UserPersonRepository userPersonRepository) {
		this.userPersonRepository = userPersonRepository;
	}



	@Override
	public List<UserPersonDTO> getAllUserPerson() {
		List<UserPerson> userPersonEntities = (List<UserPerson>) userPersonRepository.findAll();
		List<UserPersonDTO> userPersonDTOList = UserPersonMapper.INSTANCE.toDtoList(userPersonEntities);
		return userPersonDTOList;
	}



	@Override
	public UserPersonDTO findUserById(Long userId) {
		UserPersonDTO userPersonDto = UserPersonMapper.INSTANCE.toDto(userPersonRepository.findById((long) 1).get());
		return userPersonDto;
	}

}
