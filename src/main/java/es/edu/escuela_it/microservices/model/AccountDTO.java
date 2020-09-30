package es.edu.escuela_it.microservices.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AccountDTO {
	
	private Integer id;
	
	@NonNull
	private String name;

}
