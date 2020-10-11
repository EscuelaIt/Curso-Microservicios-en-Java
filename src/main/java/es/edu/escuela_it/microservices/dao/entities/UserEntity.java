package es.edu.escuela_it.microservices.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "ms_users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@NonNull
	private Integer id;
	
	@NonNull
	private String name;

	@Column(name = "last_name")
	private String lastname;

	private int edad;

}
