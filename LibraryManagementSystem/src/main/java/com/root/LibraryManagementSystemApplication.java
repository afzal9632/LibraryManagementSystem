package com.root;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring boot REST API Documentation for Library Management System",
                description = "Demo LibraryManagementSystem",
                version = "v1.0",
                contact = @Contact(
                        name = "Md Afzal",
                        email = "mdafzal9632@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Sample input",
                url = "https://drive.google.com/file/d/1zhAhc-XLsH7fmRRVTFZa07beygz72fam/view?usp=sharing"
        )
)
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

}
