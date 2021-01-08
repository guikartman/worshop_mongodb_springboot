package com.guiferreira.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guiferreira.workshopmongo.domain.User;
import com.guiferreira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		// Limpa a coleção na base de dados
		userRepository.deleteAll();

		User gustavo = new User(null, "Gustavo Dantas", "gustavo@gmail.com");
		User oscar = new User(null, "Oscar Muniz", "oscar@gmail.com");
		User jorge = new User(null, "Jorge Jesus", "jj@gmail.com");
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		// Salva os usuarios no mongoDb
		userRepository.saveAll(Arrays.asList(gustavo, oscar, jorge, maria, alex, bob));
	}

}
