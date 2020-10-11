package es.edu.escuela_it.microservices.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	
	public List<UserEntity> findByEdadLessThan(int edad);
	public List<UserEntity> findByEdadGreaterThanEqual(int edad);
	public List<UserEntity> findByNameLike(String name);
	public List<UserEntity> findByNameContaining(String name);
	
	@Query(value="select * from ms_users where name = ?1 and edad >= ?2 and edad <= ?3" ,nativeQuery = true)
	public List<UserEntity> findAllUsersBetweenAgeAndName(String name,int ageBegin,int ageEnd);

	
	

}
