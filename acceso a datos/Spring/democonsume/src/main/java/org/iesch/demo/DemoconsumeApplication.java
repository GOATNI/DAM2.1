package org.iesch.demo;

import org.iesch.demo.modelo.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoconsumeApplication {
	@Autowired
	RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(DemoconsumeApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		//Template
		String url = "https://catfact.ninja/fact";

			Fact miFact = restTemplate.getForObject(url, Fact.class);
			System.out.println(miFact);




	}*/
}
