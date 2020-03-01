package br.com.crux;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CruxApiDevApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CruxApiDevApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CruxApiDevApplication.class, args);
	}

	@PostConstruct
	public void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	}
}
