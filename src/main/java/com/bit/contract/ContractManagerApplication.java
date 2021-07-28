package com.bit.contract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ContractManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractManagerApplication.class, args);
    }

}
