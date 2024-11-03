package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.st.project_manager.Dto.UserPersonDTO;
import com.st.project_manager.Entity.UserPerson;
import com.st.project_manager.exception.handler.DuplicateKeyException;
import com.st.project_manager.repository.UserPersonRepository;

@Service
public class UserPersonServiceImpl implements UserPersonService {

	private final UserPersonRepository userPersonRepository;
	private final ModelMapper modelMapper;

	public UserPersonServiceImpl(UserPersonRepository userPersonRepository, ModelMapper modelMapper) {
		this.userPersonRepository = userPersonRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserPersonDTO createUserPerson(UserPersonDTO userPerson) {
		try {
			UserPerson userPersonToSave = modelMapper.map(userPerson, UserPerson.class);
			UserPersonDTO userPersonCreated = modelMapper.map(userPersonRepository.save(userPersonToSave),
					UserPersonDTO.class);
			return userPersonCreated;
		} catch (DataIntegrityViolationException ex) {
			throw new DuplicateKeyException(ex.getMessage());
		}
	}

	@Override
	public List<UserPersonDTO> getAllUserPerson() {
		List<UserPerson> userPersonEntities = (List<UserPerson>) userPersonRepository.findAll();
		List<UserPersonDTO> userPersonDTOList = modelMapper.map(userPersonEntities,
				new TypeToken<List<UserPersonDTO>>() {
				}.getType());
		return userPersonDTOList;
	}

	@Override
	public Optional<UserPersonDTO> getUserPersonById(Integer id) {

		UserPersonDTO userPersonDto = modelMapper.map(userPersonRepository.findById(id).get(), UserPersonDTO.class);
		return Optional.ofNullable(userPersonDto);
	}

	@Override
	public UserPersonDTO updateUserPerson(Integer id, UserPersonDTO userPerson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserPerson(Integer id) {
		// TODO Auto-generated method stub

	}

}
