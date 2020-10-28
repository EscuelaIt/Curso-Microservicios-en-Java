package es.edu.escuela_it.microservices.stories;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetUserByIdFeature {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserRepository userRepository;
	
	ResultActions action;
	
	@Given("users stored in the system")
	public void users_stored_in_the_system() throws Exception {

		UserEntity userSaved = userRepository.save(new UserEntity(1,"Rafa"));
		assertTrue(userSaved.getId() == 1);
		
	}

	@When("client makes call to GET users with id {int}")
	public void client_makes_call_to_GET_users_with_id(int userId) throws Exception {

		action=mvc.perform(get("/users/" + userId).contentType(MediaType.APPLICATION_JSON));
	}

	@Then("the client receives status code of {int} And the user id is {int}")
	public void the_client_receives_status_code_of_code_And_the_user_id_is_id(int expectedStateCode,int expectedUserId) throws Exception {
		
		action.andExpect(status().isOk());
		action.andExpect(jsonPath("id", Matchers.is(expectedUserId)));
		
	}
	
}