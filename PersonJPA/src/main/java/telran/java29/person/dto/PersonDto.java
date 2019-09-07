package telran.java29.person.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java29.person.model.Adress;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = {"id"})

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
@JsonSubTypes({
@Type(value = ChildDto.class, name = "child"),
@Type(value = EmployeeDto.class, name = "employee")
})
public class PersonDto {
	int id;
	String name;
	String birthDate;
	Adress adress;

}
