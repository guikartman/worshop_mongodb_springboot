package com.guiferreira.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiferreira.workshopmongo.domain.User;
import com.guiferreira.workshopmongo.dto.UserDTO;
import com.guiferreira.workshopmongo.repository.UserRepository;
import com.guiferreira.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> optional = repo.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Usuario com id: "+ id+ " ,não foi encontrado"));
	}

	public User insert(User user) {
		return repo.insert(user);
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
}
