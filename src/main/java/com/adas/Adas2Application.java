package com.adas;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Adas2Application implements CommandLineRunner{

//	@Autowired
//	private BCryptPasswordEncoder passEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(Adas2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String pass1 = "1234";
//		
//		System.out.println(passEncoder.encode(pass1));
	}

}
