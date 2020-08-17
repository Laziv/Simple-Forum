package ua.lviv.lgs.nl.simpleforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SimpleForumApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SimpleForumApplication.class, args);
	}

}
