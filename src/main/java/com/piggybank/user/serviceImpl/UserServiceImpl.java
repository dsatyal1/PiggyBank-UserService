package com.piggybank.user.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piggybank.user.entity.User;
import com.piggybank.user.exception.UserNotFoundException;
import com.piggybank.user.model.UserDTO;
import com.piggybank.user.repo.UserRepository;
import com.piggybank.user.service.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return saveUser(userDTO);
	}

	@Override
	public UserDTO getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(userName);
		return convertToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not Found"));
		return convertToDTO(user);
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return saveUser(userDTO);
	}

	@Override
	public UserDTO updateUser(long id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));
		user.setFirst_name(userDTO.getFirstName());
		user.setMiddle_name(userDTO.getMiddleName());
		user.setLast_name(userDTO.getLastName());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		if(userDTO.getPassword()!=null) {
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		}
		user.setDob(userDTO.getDob());
		user.setPhone_number(userDTO.getPhoneNumber());
		userRepository.save(user);
		return convertToDTO(user);
		
	}

	@Override
	public String deleteUser(Long Id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(Id).orElseThrow(()-> new UserNotFoundException("User not Found"));
		user.setDelete_at(null);
		userRepository.save(user);
		return null;
	}

	private UserDTO saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDTO convertToDTO(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
