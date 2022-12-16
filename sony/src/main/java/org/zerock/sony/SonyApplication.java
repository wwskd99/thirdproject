package org.zerock.sony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	
public class SonyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonyApplication.class, args);
	}

}
