package com.jpa.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.test.entities.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	// Method to fetch the all the users using JPQL.
	@Query("SELECT u FROM User u")
	public List<User> getAllUser();
	
	
	// Method to fetch the users by his name and city using JPQL.
	@Query("SELECT u FROM User u WHERE u.name=:n AND u.city=:c")
	public List<User> getUsersByNameAndCity(@Param("n")String name,@Param("c") String city);
	
	// Method to fetch the all users using native query.
	@Query(value="SELECT * FROM User",nativeQuery = true)
	public List<User> getUsers();

}
