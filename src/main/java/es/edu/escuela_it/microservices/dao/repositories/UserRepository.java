package es.edu.escuela_it.microservices.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.edu.escuela_it.microservices.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
	
	
	
	
	

}
