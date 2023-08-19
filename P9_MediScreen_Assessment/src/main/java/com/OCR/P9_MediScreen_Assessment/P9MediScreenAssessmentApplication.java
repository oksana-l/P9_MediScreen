package com.OCR.P9_MediScreen_Assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class P9MediScreenAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(P9MediScreenAssessmentApplication.class, args);
	}

}
