package com.jpa.test.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.jpa.test.entities.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	// Method to fetch the users by his name.
	public List<User> findByName(String name);
	
	
	// Method to fetch the users by his name and city.
	public List<User> findByNameAndCity(String name, String city);
	
	// Method to fetch the user with name starting with "Anu".
	public List<User> findByNameStartingWith(String prefix);
	
	// Method to fetch the users with name ending with "ika".
	public List<User> findByNameEndingWith(String s);
	
	// Method to fetch the users with salary between 0 to 50000.
	public List<User> findBySalaryBetween(int starting, int ending);
	
	// Method to fetch the users with age above or equal to 20..
	public List<User> findByAgeGreaterThanEqual(int age);
}