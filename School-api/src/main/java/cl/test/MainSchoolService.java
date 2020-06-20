package cl.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainSchoolService {

	public static void main(String[] args) {
		setSwaggerProperties();
		SpringApplication.run(MainSchoolService.class, args);
	}
	
	private static void setSwaggerProperties() {
		System.getProperties().put("server.servlet.context-path", "/school/api");
		System.getProperties().put("server.port", "8090");
	}
}

