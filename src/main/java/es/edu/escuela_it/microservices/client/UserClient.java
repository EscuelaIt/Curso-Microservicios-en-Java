package es.edu.escuela_it.microservices.client;

import es.edu.escuela_it.microservices.model.UserDTO;

public interface UserClient {
	
	public UserDTO getUser(Integer id);

}
