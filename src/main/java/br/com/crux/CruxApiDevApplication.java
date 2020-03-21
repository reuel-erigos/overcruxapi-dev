package br.com.crux;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class CruxApiDevApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CruxApiDevApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CruxApiDevApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
