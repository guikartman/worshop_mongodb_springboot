package com.guiferreira.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guiferreira.workshopmongo.domain.Post;
import com.guiferreira.workshopmongo.domain.User;
import com.guiferreira.workshopmongo.dto.AuthorDTO;
import com.guiferreira.workshopmongo.repository.PostRepository;
import com.guiferreira.workshopmongo.repository.UserRepository;

/**
 * Classe faz a instancia de alguns objetos nas coleções.
 * 
 * @author guilherme
 *
 */
@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		// Limpa a coleção na base de dados
		userRepository.deleteAll();
		
		// Limpa a coleção na base de dados
		postRepository.deleteAll();

		User gustavo = new User(null, "Gustavo Dantas", "gustavo@gmail.com");
		User oscar = new User(null, "Oscar Muniz", "oscar@gmail.com");
		User jorge = new User(null, "Jorge Jesus", "jj@gmail.com");
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// Salva os usuarios no mongoDb
		userRepository.saveAll(Arrays.asList(gustavo, oscar, jorge, maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		// Salva os posts no mongoDb
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
