package telran.java29.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Service;

import telran.java29.person.dao.PersonRepository;
import telran.java29.person.dto.PersonDto;
import telran.java29.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = convertToPerson(personDto);
//		
//		Example<Person> employeeExample = Example.of(person);
//		Iterable<Person> employees = personRepository.findAll(employeeExample);
//        for (Person e : employees) {
//            System.out.println(e);
//        }
		
		
		personRepository.save(person);
		return true;
	}

	@Override
	public PersonDto findPersonById(int id) {
		Person person =  personRepository.findById(id).orElse(null);
		return person == null ? null : convertToPersonDto(person);
	}
	
	
	@Override
	public Iterable<PersonDto> findPersonByName(String name) {
		Person person = new Person();
		person.setName(name);
		Example<Person> example = Example.of(person);
		return personRepository.findAll(example).stream()
				.map(this::convertToPersonDto)	
				.collect(Collectors.toList());
	}
	
	
	@Override
	public Iterable<PersonDto> findByNameDublicat() {
		
		
		return personRepository.findByNameDublicat().stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}
	
	private PersonDto convertToPersonDto(Person person) {
		return PersonDto.builder()
				.id(person.getId())
				.name(person.getName())
				.birthDate(person.getBirthDate().toString())
				.build();
		
	}
	
	

	private Person convertToPerson(PersonDto personDto) {
		return Person.builder()
				.id(personDto.getId())
				.name(personDto.getName())
				.birthDate(LocalDate.parse(personDto.getBirthDate()))
				.build();
	}

	

	

	

}
