package es.edu.escuela_it.microservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.edu.escuela_it.microservices.model.AccountDTO;
import es.edu.escuela_it.microservices.model.UserDTO;

@RestController
@RequestMapping("/users")
public class UsersControllerRest {

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {

		System.out.println("Recovery user by id");

		UserDTO userDTO = new UserDTO(1, "Rafael");
		userDTO.setEdad(38);
		userDTO.setLastname("Benedettelli");

		Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
		userDTO.add(withSelfRel);

		if (userDTO == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(userDTO);
	}

	@GetMapping
	public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(@RequestParam(required = false) String name,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) Integer age) {

		List<UserDTO> list = List.of(new UserDTO(1, "Rafael"), new UserDTO(2, "Miguel"), new UserDTO(3, "Alvaro"));

		for (UserDTO userDTO : list) {

			Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
			userDTO.add(withSelfRel);

			Link accountsRel = linkTo(methodOn(UsersControllerRest.class).getUserAccounts(userDTO.getId()))
					.withRel("accounts");
			userDTO.add(accountsRel);

		}

		Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers("", "", 0)).withSelfRel();
		CollectionModel<UserDTO> result = CollectionModel.of(list, link);
		return ResponseEntity.ok(result);

		// list = list.stream().filter(u ->
		// u.getName().contains(name)).collect(Collectors.toList());

	}

	@PostMapping
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {

		System.out.println("Creating user " + userDTO.getName());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

		System.out.println("updating data");

		// buscar el user by id
		// update

		return ResponseEntity.ok(userDTO);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

		System.out.println("delete user by id");

		return ResponseEntity.ok(null);

	}

	@GetMapping("/{id}/accounts")
	public ResponseEntity<List<AccountDTO>> getUserAccounts(@PathVariable Integer id) {

		List<AccountDTO> list = List.of(new AccountDTO("google"), new AccountDTO("twitter"),
				new AccountDTO("escuelaIT"));

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}/accounts/{idAccount}")
	public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Integer id, @PathVariable Integer idAccount) {

		return ResponseEntity.ok(new AccountDTO("google"));
	}
}
