package com.guiferreira.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guiferreira.workshopmongo.domain.User;
import com.guiferreira.workshopmongo.dto.UserDTO;
import com.guiferreira.workshopmongo.services.UserService;

/**
 * Classe responsavel por criar os endpoints Rest para a coleção de Usuario.
 * 
 * @author guilherme
 *
 */
@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		// Lambda que converte cada User na lista em UserDTO
		List<UserDTO> listDTO = list.stream().map((x -> new UserDTO(x))).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
		User user = userService.fromDTO(dto);
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDTO(user));
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto, @PathVariable String id) {
		User user = userService.fromDTO(dto);
		user.setId(id);
		user = userService.update(user);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
}
