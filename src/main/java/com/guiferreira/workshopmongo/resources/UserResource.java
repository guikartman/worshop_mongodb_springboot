package com.guiferreira.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
