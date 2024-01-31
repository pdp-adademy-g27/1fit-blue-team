package com.example.onefit;

import lombok.RequiredArgsConstructor;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class OneFitApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneFitApplication.class, args);
    }

    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }
}
