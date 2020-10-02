package es.edu.escuela_it.microservices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import es.edu.escuela_it.microservices.model.UserDTO;


@Service
@Qualifier("BD")
@ConditionalOnProperty(prefix = "app",name = "edition",havingValue = "Community")
public class UserServiceImpl implements UserService {
	
	public Optional<UserDTO> getUserById(Integer id) {
		
		UserDTO userDTO = new UserDTO(1, "Rafael");
		
		return Optional.ofNullable(userDTO);

	}
	

}
