package com.jpa.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class BootjpaexampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BootjpaexampleApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);

		// Saving one object in one go.--------------------------------------------

		/*
		 * User user=new User(); user.setName("Anuprash Gautam"); user.setCity("Delhi");
		 * user.setStatus("I am java programmer.");
		 * 
		 * User user1 = userRepository.save(user);
		 * 
		 * System.out.println(user1);
		 */

		// Saving multiple users in one go.-----------------------------------------

		/*
		 * List<User> users=new ArrayList();
		 * 
		 * User user=new User(); user.setName("Anuprash Gautam"); user.setCity("Delhi");
		 * user.setStatus("I am java programmer."); users.add(user);
		 * 
		 * User user1=new User(); user.setName("Ankit Kumar"); user.setCity("Lucknow");
		 * user.setStatus("I am Python Programmer."); users.add(user1);
		 * 
		 * // Or we can do something like this: // List<User> users=List.of(user,user1);
		 * 
		 * Iterable<User> result = userRepository.saveAll(users);
		 * result.forEach(fetchedUser->System.out.println(fetchedUser));
		 */
		
		

		// Updating the entity using update() method

		/*
		 * Optional<User> optional = userRepository.findById(2); User user =
		 * optional.get(); user.setName("Anuprash Sharma"); User result =
		 * userRepository.save(user); System.out.println(result);
		 * 
		 */
		
		
		
		// Getting the entity using the get() method.
		
		/*
		 * Optional<User> optional = userRepository.findById(1); User user =
		 * optional.get(); System.out.println(user);
		 */
		
		
		
		// Using the findAll() method to get all the records in iterable.
		
		Iterable<User> itr = userRepository.findAll();
		
		// 1. First method to iterate the records.
		/*
		 * Iterator<User> iterator = itr.iterator(); while(iterator.hasNext()) { User
		 * user=iterator.next(); System.out.println(user); }
		 */
		
		// 2. Second method is to use the forEach() method with Consumer.
		/*
		 * itr.forEach(new Consumer<User>() {
		 * 
		 * @Override public void accept(User t) { System.out.println(t); } });
		 */
		
		// 3. Third method is to use the forEach() method with lambda expression.
		/* itr.forEach(u->System.out.println(u)); */
		
		
		
		
		// Deleting the entity.
		
		// Deleting the single element using the id.
		/*
		 * userRepository.deleteById(1);
		 * System.out.println("Deleted the user with id 1.");
		 */
		
		// Deleting all the elements.
		Iterable<User> users = userRepository.findAll();
		userRepository.deleteAll(users);
		System.out.println("Deleted all the users.");
	}

}
