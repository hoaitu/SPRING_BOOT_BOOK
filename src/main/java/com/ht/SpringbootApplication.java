package com.ht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ht" })
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}

//@SpringBootApplication
//@ComponentScan(basePackages={"com.technicalkeeda"})
//public class Application {
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//}