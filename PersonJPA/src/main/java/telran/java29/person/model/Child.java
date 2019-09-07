package telran.java29.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Child extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String kindergarten;

	public Child(Integer id, String name, LocalDate birthDate, Adress adress, String kindergartenString) {
		super(id, name, birthDate, adress);
		this.kindergarten = kindergartenString;
	}

	
	
	

}
