package com.OCR.P9_MediScreen_UI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class P9MediScreenUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(P9MediScreenUiApplication.class, args);
	}
}
