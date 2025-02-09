# New way to authorize the application using Spring Security after WebSecurityConfigurer class deprication in version 5.7


```java

package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyConfig {

	// It helps in encoding the password so that if a person get access to the DB, can't use the user profile.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Provides the user details.
    @Bean 
    public UserDetailsService getUserDetailsService() {
    	return new UserDetailsServiceImpl();
    }
    
    // It authenticate the user by using the getUserDetailsService, and passwordEncoder beans.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    	
    	daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	
    	return daoAuthenticationProvider;
    }
    
    
    // Autherization based on the http request is managed here.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())  // Disable CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/**").permitAll()
            )
            //.formLogin(Customizer.withDefaults())  // Default Spring Security login form
            .formLogin(form -> form
                    .loginPage("login")   // Specify your custom login page URL
                    .permitAll()           // Allow all users to access the login page
                )
            .build();
    }
}


```

Spring Security helps secure your application, like how you lock your doors to prevent strangers from entering. It ensures that only authorized people (users with the correct username and password) can access certain parts of your app. Think of it like a security guard that checks if you have permission to enter different sections of a building.

Here’s how the code works in simple terms:

1. **PasswordEncoder Bean**:
   - `passwordEncoder()` creates a way to safely handle passwords. It uses `BCryptPasswordEncoder`, which is a secure way to encode passwords. This means when a user enters a password, it’s not stored directly but as a coded version (hash). This way, even if someone accesses your database, they can’t see the actual passwords.

2. **UserDetailsService Bean**:
   - `getUserDetailsService()` is a way to get information about a user. This bean calls a custom service `UserDetailsServiceImpl`, which is responsible for loading user-specific data like username and password.

3. **DaoAuthenticationProvider Bean**:
   - `authenticationProvider()` helps Spring Security figure out how to authenticate a user. It uses the `UserDetailsService` to fetch user details (username, password) and the `PasswordEncoder` to check if the password is correct. If the username and password match, the user is allowed access.

4. **SecurityFilterChain Bean**:
   - This bean defines the security rules for your application.
     - `csrf.disable()` turns off CSRF (Cross-Site Request Forgery) protection. It’s a special kind of attack where a malicious user can perform actions on behalf of someone else. Disabling CSRF here might be okay in some cases, but it’s usually kept on for security.
     - `.authorizeHttpRequests(auth -> auth...)` specifies which pages can be accessed by which roles. For example, pages starting with `/user/` require the user to have the role `USER`, while pages starting with `/admin/` require the role `ADMIN`. Other pages (`/**`) are accessible to everyone.
     - `.formLogin()` enables the default login page provided by Spring Security.

In short, this code sets up basic security for your application by defining how users log in and which parts of the app they can access based on their roles.