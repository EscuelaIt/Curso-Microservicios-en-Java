package es.edu.escuela_it.microservices.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import es.edu.escuela_it.microservices.mappers.UserMapper;
import es.edu.escuela_it.microservices.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Slf4j
public class UserServiceImplTest {

	private static final int QTY_USERS = 80;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	private static List<UserEntity> userEntities;

	private static List<UserDTO> usersDtos;

	private static PodamFactory factory;
	
	private static Pageable pageable;

	@BeforeAll
	static void setup() {

		PageRequest pageRequest = PageRequest.of(0, 10);
		pageable = pageRequest.next();
		
		factory = new PodamFactoryImpl();
		factory.getStrategy().setMemoization(false);

		userEntities = new ArrayList<>();
		usersDtos = new ArrayList<>();
		
		for (int i = 0; i < QTY_USERS; i++) {
			
			UserEntity usersEntities = factory.manufacturePojoWithFullData(UserEntity.class);
			userEntities.add(usersEntities);
			
			UserDTO userDto = factory.manufacturePojoWithFullData(UserDTO.class);
			usersDtos.add(userDto);
		}
		
		
	}

	@BeforeEach
	public void setUpBefore() {

		log.info("@BeforeAll - executes once before all test methods in this class");

		MockitoAnnotations.initMocks(this);

		//Construccion automatica por @injectMocks
		//userServiceImpl = new UserServiceImpl(userRepository, userMapper);

		log.info("Build Mock for userRepository");
		
		Page<UserEntity> pagedUserEntities = new PageImpl<>(userEntities);
		
		Mockito.when(userRepository.findAll(pageable)).thenReturn(pagedUserEntities);
		Mockito.when(userMapper.getUsersDtos(userEntities)).thenReturn(usersDtos);

		log.info("@BeforeEach - executed before each test method");

	}

	//@Disabled
    //@EnabledOnOs(OS.LINUX)
	@Test
    @DisplayName("test list all Users")
    @Order(1)
	public void test_when_listUser_then_returnAllUsers() {

		final int EXPECTED_SIZE = 9;
		
		log.info("Get all users");

		// Efective Test method
		List<UserDTO> listAllUsers = userServiceImpl.listAllUsers(pageable);

		// Mockito verify
		verify(userRepository, atLeast(1)).findAll(pageable);
		verify(userMapper, atLeast(1)).getUsersDtos(userEntities);

		// Asserts
		assertTrue(listAllUsers.size() > EXPECTED_SIZE);
		Optional<UserDTO> first = listAllUsers.stream().findFirst();
		assertTrue(first.get().getTitle().contains("Developer"));
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	public final void sum(int i) {

		log.info("valor i " + i);
		assertTrue(i > 0);
	}


	@AfterEach
	void tearDown() {
		log.info("@AfterEach - executed after each test method.");
	}

	@AfterAll
	static void done() {
		log.info("@AfterAll - executed after all test methods.");

	}

}
