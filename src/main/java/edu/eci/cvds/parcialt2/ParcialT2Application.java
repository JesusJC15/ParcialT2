package edu.eci.cvds.parcialt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.Generated;

@SpringBootApplication
@EnableMongoRepositories
@Generated
public class ParcialT2Application {

	public static void main(String[] args) {
		SpringApplication.run(ParcialT2Application.class, args);
	}

}
