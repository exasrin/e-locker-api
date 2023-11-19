package com.example.lock_and_load.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Lock And Load",
        version = "1.0",
        contact = @Contact(
                name = "Example",
                url = "https://esample.com"
        )
    )
)
public class OpenAPIConfiguration {
}
