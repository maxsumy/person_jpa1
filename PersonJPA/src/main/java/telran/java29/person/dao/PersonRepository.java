package telran.java29.person.dao;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java29.person.dto.CityPopulationDto;
import telran.java29.person.dto.PersonDto;
import telran.java29.person.model.Child;
import telran.java29.person.model.Employee;
import telran.java29.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
//	@Query("SELECT name, count(*) FROM Person group by name having count(*)>1")
//	@Query("SELECT * FROM person where \"name\" = 'anna'")
	
//	@Query(value = "SELECT name, count(*) FROM Person group by name having count(*)>1)", nativeQuery = true)
	
	@Query(value = "select * from person where name in(select name from person group by name HAVING count(name) > 1)", nativeQuery = true)
	List<Person> findByNameDublicat();
	
	List<Person> findByAdressCity(String city);

	@Query("select p from Person p where p.salary between :min and :max")
	List<Person> findBySalaryBetween(@Param("min") int min, @Param("max") int max);

	List<Child> findBy();

	@Query("select new telran.java29.person.dto.CityPopulationDto(p.adress.city, count(p)) from Person p"
			+ " group by p.adress.city order by count(p) desc")
	Iterable<CityPopulationDto> getCitiesPopulation();
	
	@Query("select p from Person p where p.name=?1")
	Stream<Person> findByName(String name);
	
	

}
