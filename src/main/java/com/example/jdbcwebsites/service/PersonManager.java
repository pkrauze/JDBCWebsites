package main.java.com.example.jdbcwebsites.service;

import java.util.List;

import com.example.jdbcdemo.domain.Person;

public interface PersonManager {
	
	public int addPerson(Person person);
	public List<Person> getAllPersons();

}
