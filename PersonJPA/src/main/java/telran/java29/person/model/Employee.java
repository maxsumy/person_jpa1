package telran.java29.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String company;
	int salary;
	public Employee(Integer id, String name, LocalDate birthDate, Adress adress, String company, int salary) {
		super(id, name, birthDate, adress);
		this.company = company;
		this.salary = salary;
	}
	
	

}
