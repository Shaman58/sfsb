package ru.erp.sfsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class SfsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfsbApplication.class, args);
	}

}