package es.edu.escuela_it.microservices.model;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO  extends RepresentationModel<UserDTO> {

	@NonNull
	private Integer id;

	@NonNull
	private String name;

	@NotNull
	private String lastname;

	@ToString.Exclude
	private int edad;

}
