package telran.java29.person.service;

import telran.java29.person.dto.PersonDto;

public interface PersonService {
	
	boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(int id);

	Iterable<PersonDto> findPersonByName(String name);

	Iterable<PersonDto> findByNameDublicat();

}
