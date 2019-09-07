package telran.java29.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java29.person.model.Adress;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends PersonDto {
	String company;
	Integer salary;

	public EmployeeDto(int id, String name, String birthDate, Adress address, String company, Integer salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}

}