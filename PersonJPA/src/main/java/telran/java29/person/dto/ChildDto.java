package telran.java29.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java29.person.model.Adress;

@NoArgsConstructor
@Getter
@Setter
public class ChildDto extends PersonDto {
	String kindergarten;

	public ChildDto(int id, String name, String birthDate, Adress address, String kindergarten) {
		super(id, name, birthDate, address);
		this.kindergarten = kindergarten;
	}

}
