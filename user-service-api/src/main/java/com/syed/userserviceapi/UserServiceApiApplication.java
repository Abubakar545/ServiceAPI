package com.syed.userserviceapi;

import com.syed.userserviceapi.dto.AccountsContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
public class UserServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApiApplication.class, args);
	}

}
