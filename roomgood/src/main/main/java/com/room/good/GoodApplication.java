package com.room.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 날짜 자동으로 수집하는?
public class GoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodApplication.class, args);
	}

}
