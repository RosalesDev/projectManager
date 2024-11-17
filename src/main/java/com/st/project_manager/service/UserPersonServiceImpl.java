package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.UserPersonDTO;
import com.st.project_manager.entity.UserPerson;
import com.st.project_manager.repository.UserPersonRepository;

import constant.UserStatus;

@Service
public class UserPersonServiceImpl implements UserPersonService {

	private final UserPersonRepository userPersonRepository;
	private final ModelMapper modelMapper;

	public UserPersonServiceImpl(UserPersonRepository userPersonRepository, ModelMapper modelMapper) {
		this.userPersonRepository = userPersonRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public UserPersonDTO createUserPerson(UserPersonDTO userPerson) {
		UserPerson userPersonToSave = modelMapper.map(userPerson, UserPerson.class);
		UserPersonDTO userPersonCreated = modelMapper.map(userPersonRepository.save(userPersonToSave),
				UserPersonDTO.class);
		return userPersonCreated;
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserPersonDTO> getAllUserPerson() {
		List<UserPerson> userPersonEntities = (List<UserPerson>) userPersonRepository.findAll();
		List<UserPersonDTO> userPersonDTOList = modelMapper.map(userPersonEntities,
				new TypeToken<List<UserPersonDTO>>() {
				}.getType());
		return userPersonDTOList;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<UserPersonDTO> getUserPersonById(Integer id) {

		UserPersonDTO userPersonDto = modelMapper.map(userPersonRepository.findById(id).get(), UserPersonDTO.class);
		return Optional.ofNullable(userPersonDto);
	}

	@Transactional
	@Override
	public UserPersonDTO updateUserPerson(Integer id, UserPersonDTO userPerson) {
		UserPersonDTO userPersonToUpdateDTO = modelMapper.map(userPersonRepository.findById(id).get(), UserPersonDTO.class);
		if (userPerson.getEmail() != null && !userPerson.getEmail().isBlank()) {
			userPersonToUpdateDTO.setEmail(userPerson.getEmail());
		}

		if (userPerson.getFirstname() != null && !userPerson.getFirstname().isBlank()) {
			userPersonToUpdateDTO.setFirstname(userPerson.getFirstname());

		}

		if (userPerson.getLastname() != null && !userPerson.getLastname().isBlank()) {
			userPersonToUpdateDTO.setLastname(userPerson.getLastname());
		}

		if (userPerson.getUsername() != null && !userPerson.getUsername().isBlank()) {
			userPersonToUpdateDTO.setUsername(userPerson.getUsername());
		}

		UserPerson userPersonUdated = modelMapper.map(userPersonToUpdateDTO, UserPerson.class);
		return modelMapper.map(userPersonRepository.save(userPersonUdated), UserPersonDTO.class);
	}

	@Transactional
	@Override
	public Optional<UserPersonDTO> deleteUserPerson(Integer id) {

		Optional<UserPerson> userPerson = userPersonRepository.findById(id);

		if (userPerson.isPresent()) {
			UserPersonDTO userPersonDTO = modelMapper.map(userPerson, UserPersonDTO.class);
			userPersonDTO.setStatus(UserStatus.DEACTIVE);
			userPersonRepository.save(modelMapper.map(userPersonDTO, UserPerson.class));
			return Optional.of(userPersonDTO);
		}
		return Optional.empty();
	}

}
