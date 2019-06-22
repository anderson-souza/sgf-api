package com.SGFApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.SGFApi.config.WebSecurityConfig;

@SpringBootApplication
@Import({ WebSecurityConfig.class })
public class SgfApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgfApiApplication.class, args);
	}

}
