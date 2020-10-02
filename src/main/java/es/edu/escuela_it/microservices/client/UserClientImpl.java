package es.edu.escuela_it.microservices.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.edu.escuela_it.microservices.model.UserDTO;

@Service
public class UserClientImpl implements UserClient {

	@Override
	public UserDTO getUser(Integer id) {

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://jsonplaceholder.typicode.com/posts/1";
		UserDTO user = restTemplate.getForObject(fooResourceUrl, UserDTO.class);
		return user;

	}

}
