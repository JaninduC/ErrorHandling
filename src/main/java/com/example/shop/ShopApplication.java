package com.example.shop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Shop Application API",
                description = "This is test application",
                version = "1.0.1v",
                contact = @Contact(
                        name = "Janindu C Perera",
                        email = "Janindu.c.perera@gamil.com"
                ),
                license = @License(
                        name = "license",
                        url = "url"
                )
        )
)
//@EnableSwagger2
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
