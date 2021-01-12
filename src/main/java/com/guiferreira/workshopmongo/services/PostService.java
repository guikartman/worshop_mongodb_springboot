package com.guiferreira.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiferreira.workshopmongo.domain.Post;
import com.guiferreira.workshopmongo.domain.User;
import com.guiferreira.workshopmongo.dto.UserDTO;
import com.guiferreira.workshopmongo.repository.PostRepository;
import com.guiferreira.workshopmongo.repository.UserRepository;
import com.guiferreira.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> optional = repo.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado"));
	}
}
