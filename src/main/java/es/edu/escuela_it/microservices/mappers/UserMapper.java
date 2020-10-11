package es.edu.escuela_it.microservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.model.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {  
	
	public UserEntity getUserEntity(UserDTO userDTO);
	
	public UserDTO getUserDTO(UserEntity userEntity);
	
	public List<UserDTO> getUsersDtos(List<UserEntity> usersEntities);
	
	
}

