package es.edu.escuela_it.microservices.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import es.edu.escuela_it.microservices.configurations.ConfigHatoeasTest;
import es.edu.escuela_it.microservices.model.UserDTO;
import es.edu.escuela_it.microservices.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Disabled
@DisplayName("Test userController : Controller Layer")
@WebMvcTest(UsersControllerRest.class)
@Import(ConfigHatoeasTest.class)
@Slf4j
public class UserRestControllerIntegrationTest {

	@MockBean
	private UserServiceImpl userServiceImpl;

	@Autowired
	private MockMvc mvc;
	
	private static Pageable pageable;

	@BeforeEach
	public void setUpBefore() {

		log.info("Init test");
		
		PageRequest pageRequest = PageRequest.of(0, 10);
		pageable = pageRequest.next();

		MockitoAnnotations.initMocks(this);

		List<UserDTO> users = List.of(new UserDTO(1, "Rafael"), new UserDTO(2, "Miguel"), new UserDTO(3, "Alvaro"));
		Mockito.when(userServiceImpl.listAllUsers(pageable)).thenReturn(users);

		log.info("list all");
		//List<UserDTO> listAll = userServiceImpl.listAllUsers(pageable);
		//listAll.forEach(t -> log.info("usuario  " + t));
	}

	@Test
	@DisplayName("Test get all users from rest controller")
	public void givenUsers_whenGetUsers_thenReturnJsonArrayWithUsers() throws Exception {

				// JsonPath y Hamcrest
				mvc.perform(get("/users/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content", hasSize(3)))
				.andExpect(jsonPath("$.content[0].name", is("Rafael")))
				.andExpect(jsonPath("$.content[1].name", is("Miguel")))
				.andExpect(jsonPath("$.content[2].name", is("Alvaro")));

	}

}
