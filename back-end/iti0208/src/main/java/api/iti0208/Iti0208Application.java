package api.iti0208;

import api.iti0208.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Iti0208Application {

    public static void main(String[] args) {
        SpringApplication.run(Iti0208Application.class, args);
    }

}

