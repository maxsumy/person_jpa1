package telran.java29.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.java29.person.dao.PersonRepository;
import telran.java29.person.dto.ChildDto;
import telran.java29.person.dto.CityPopulationDto;
import telran.java29.person.dto.EmployeeDto;
import telran.java29.person.dto.PersonDto;
import telran.java29.person.model.Adress;
import telran.java29.person.model.Child;
import telran.java29.person.model.Employee;
import telran.java29.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Transactional
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
	
	@Override
	@Transactional
	public PersonDto updateAdress(int id, Adress adress) {
		Person person = personRepository.findById(id).orElse(null);
		if(person == null) {
			return null;
		}
		person.setAdress(adress);
		personRepository.save(person);
		return convertToPersonDto(person);
	}
	
	private PersonDto convertToPersonDto(Person person) {
		if (person instanceof Employee) {
			Employee employee = (Employee) person;
			return new EmployeeDto(employee.getId(), employee.getName() 
					, employee.getBirthDate().toString(), employee.getAdress()
					, employee.getCompany(), employee.getSalary());
		}
		if (person instanceof Child) {
			Child child = (Child) person;
			return new ChildDto(child.getId(), child.getName() 
					, child.getBirthDate().toString(), child.getAdress()
					, child.getKindergarten());
		}
		return PersonDto.builder()
				.id(person.getId())
				.name(person.getName())
				.birthDate(person.getBirthDate().toString())
				.adress(person.getAdress())
				.build();
		
	}
	
	

	private Person convertToPerson(PersonDto personDto) {
		if (personDto instanceof EmployeeDto) {
			EmployeeDto employeeDto = (EmployeeDto) personDto;
			return new Employee(employeeDto.getId(), employeeDto.getName()
					, LocalDate.parse(employeeDto.getBirthDate()), employeeDto.getAdress()
					, employeeDto.getCompany(), employeeDto.getSalary());
		}
		if (personDto instanceof ChildDto) {
			ChildDto childDto = (ChildDto) personDto;
			return new Child(childDto.getId(), childDto.getName()
					, LocalDate.parse(childDto.getBirthDate()), childDto.getAdress()
					, childDto.getKindergarten());
		}
		return Person.builder()
				.id(personDto.getId())
				.name(personDto.getName())
				.birthDate(LocalDate.parse(personDto.getBirthDate()))
				.adress(personDto.getAdress())
				.build();
	}

	@Override
	public Iterable<PersonDto> findPersonByCity(String city) {
		
		return personRepository.findByAdressCity(city).stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findEmployeesBySalary(int min, int max) {
		
		return personRepository.findBySalaryBetween(min, max).stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findAllChildren() {
		
		return personRepository.findBy().stream()
				.map(this::convertToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDto> getPopulation() {
		
		return personRepository.getCitiesPopulation();
	}

	

}
