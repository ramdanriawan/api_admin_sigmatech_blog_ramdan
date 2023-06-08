package com.bikinaplikasi.karirku;

import com.bikinaplikasi.karirku.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class TritonikApplication {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(TritonikApplication.class, args);
	}
}
