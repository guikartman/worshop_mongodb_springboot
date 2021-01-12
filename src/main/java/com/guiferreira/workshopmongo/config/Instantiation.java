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
import com.guiferreira.workshopmongo.dto.CommentDTO;
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
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().add(c3);
		
		// Salva os posts no mongoDb
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
	}

}
