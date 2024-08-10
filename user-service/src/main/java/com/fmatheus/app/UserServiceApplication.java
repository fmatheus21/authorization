package com.fmatheus.app;

import com.fmatheus.app.hexagonal.application.domain.AddressDomain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);

		AddressDomain domain=new AddressDomain();
		domain.setDistrict("HHHH");
		//domain.setPlace(null);

		System.out.println("domain: "+domain);
	}

}
