package es.edu.escuela_it.microservices.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import es.edu.escuela_it.microservices.validators.CIF;
import es.edu.escuela_it.microservices.validators.GroupValidatorOnCreate;
import es.edu.escuela_it.microservices.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "System user")
public class UserDTO extends RepresentationModel<UserDTO> {

	@NonNull
	@NotNull
	@ApiModelProperty(notes = "Unique identifier of the User.", example = "1", required = true, position = 0)
	private Integer id;

	@NonNull
	@NotBlank
	private String name;

	@NotNull
	@Size(min = 6,max = 20)
	@Column(name = "last_name")
	private String lastname;

	@Positive
	@Min(18)
	@Max(90)
	@ToString.Exclude
	private int edad;
	
	@Email
	@ApiModelProperty(example = "string@gmail.com")
	private String email;

	//@AssertFalse
	@AssertTrue(message = "{app.field.active.error}",groups = GroupValidatorOnCreate.class)
	@AssertFalse(message = "debe ser falso",groups = GroupValidatorOnUpdate.class)
	private boolean active;

	//@FutureOrPresent
	@Past(message = "{app.field.birth_day.error}")
	@ApiModelProperty(example = "1982-01-19")
	private LocalDate birthDay;
	
	@CIF
	private String cif;
	
	private String title;
	
	private String body;
}
