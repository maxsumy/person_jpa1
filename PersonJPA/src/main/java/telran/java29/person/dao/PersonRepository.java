package telran.java29.person.dao;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java29.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
//	@Query("SELECT name, count(*) FROM Person group by name having count(*)>1")
//	@Query("SELECT * FROM person where \"name\" = 'anna'")
	
	@Query(value = "select * from person where name in(select name from person group by name HAVING count(name) > 1)", nativeQuery = true)
	List<Person> findByNameDublicat();

}
