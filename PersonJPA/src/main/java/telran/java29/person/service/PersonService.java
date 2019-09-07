package telran.java29.person.service;

import telran.java29.person.dto.CityPopulationDto;
import telran.java29.person.dto.PersonDto;
import telran.java29.person.model.Adress;

public interface PersonService {
	
	boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(int id);

	Iterable<PersonDto> findPersonByName(String name);

	Iterable<PersonDto> findByNameDublicat();

	PersonDto updateAdress(int id, Adress adress);

	Iterable<PersonDto> findPersonByCity(String ir);

	Iterable<PersonDto> findEmployeesBySalary(int min, int max);

	Iterable<PersonDto> findAllChildren();

	Iterable<CityPopulationDto> getPopulation();

}
