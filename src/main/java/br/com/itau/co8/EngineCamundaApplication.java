package br.com.itau.co8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import feign.Logger;

@SpringBootApplication
public class EngineCamundaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineCamundaApplication.class, args);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}
