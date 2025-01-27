package com.jpa.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.aspectj.apache.bcel.classfile.Module.Uses;
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

		List<User> users = userRepository.findByName("Anuprash");
		users.forEach(u->System.out.println(u));
		
		System.out.println("___________________________________________________");
		
		List<User> users1 = userRepository.findByNameAndCity("Anuprash","Delhi");
		users1.forEach(u->System.out.println(u));
		
		System.out.println("___________________________________________________");
		
		List<User> users2 = userRepository.findByNameStartingWith("Anu");
		users2.forEach(u->System.out.println(u));

		System.out.println("___________________________________________________");

		List<User> users3 = userRepository.findByNameEndingWith("ika");
		users3.forEach(u->System.out.println(u));
		
		System.out.println("___________________________________________________");

		List<User> users4 = userRepository.findBySalaryBetween(0, 50000);
		users4.forEach(u->System.out.println(u));
		
		System.out.println("___________________________________________________");
		
		List<User> users5 = userRepository.findByAgeGreaterThanEqual(20);
		users5.forEach(u->System.out.println(u));

	}

}
