package telran.java29.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.person.dto.CityPopulationDto;
import telran.java29.person.dto.PersonDto;
import telran.java29.person.model.Adress;
import telran.java29.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPerson(@PathVariable int id) {
		return personService.findPersonById(id);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonByName(@PathVariable String name) {
		return personService.findPersonByName(name);
	}
	
	@GetMapping
	public Iterable<PersonDto> findByNameDublicat() {
		return personService.findByNameDublicat();
	}
	
	@PutMapping("/adress/{id}")
	public PersonDto updateAdress(@PathVariable int id, @RequestBody Adress adress) {
		return personService.updateAdress(id, adress);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonByCity(@PathVariable("city") String ir){
		return personService.findPersonByCity(ir);
	}
	
	@GetMapping("/salary/{min}/{max}")
	public Iterable<PersonDto> findEmployeesBySalary(@PathVariable int min, @PathVariable int max){
		return personService.findEmployeesBySalary(min, max);
	}
	
	@GetMapping("/children")
	public Iterable<PersonDto> findAllChildren(){
		return personService.findAllChildren();
	}
	
	@GetMapping("/population")
	public Iterable<CityPopulationDto> getPopulation(){
		return personService.getPopulation();
	}

}
