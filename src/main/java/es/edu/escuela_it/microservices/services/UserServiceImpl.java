package es.edu.escuela_it.microservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import es.edu.escuela_it.microservices.mappers.UserMapper;
import es.edu.escuela_it.microservices.model.UserDTO;


@Service
@Qualifier("BD")
@ConditionalOnProperty(prefix = "app",name = "edition",havingValue = "Community")
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository,UserMapper userMapper) {
		
		this.userRepository = userRepository;
		this.userMapper = userMapper;

	}
	
	public Optional<UserDTO> getUserById(Integer id) {
		
		Optional<UserEntity> userOptional = userRepository.findById(id);
		
		if (userOptional.isPresent()) {
			UserEntity userEntity = userOptional.get();
			UserDTO userDTO = userMapper.getUserDTO(userEntity);
			return Optional.of(userDTO);
		}
		
		return Optional.empty();

	}

	@Override
	public List<UserDTO> listAllUsers(Pageable pageable) {
		
		 Page<UserEntity> usersEntitiesPage = userRepository.findAll(pageable);
		 List<UserEntity> userEntititesList = usersEntitiesPage.getContent();
		 List<UserDTO> usersDtos = userMapper.getUsersDtos(userEntititesList);
		 
		 //Minima logica de negocios. 
		 //Esta logica es la que se somete a testing en UserServiceImplTest
		 usersDtos.forEach(user -> user.setTitle("Developer " + user.getName()));
		
		return usersDtos;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		
		UserEntity userEntity = userMapper.getUserEntity(userDTO);
		userEntity = userRepository.save(userEntity);
		
		return userDTO;
	}

	@Override
	public void deleteById(Integer id) {

		userRepository.deleteById(id);
		
	}
	

}
