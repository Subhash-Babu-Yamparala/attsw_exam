package com.attsw.attsw_exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories/*(repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)*/
@EnableJpaAuditing(dateTimeProviderRef = "dateAuditingProvider")
@SpringBootApplication
public class AttswExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttswExamApplication.class, args);
	}

}
