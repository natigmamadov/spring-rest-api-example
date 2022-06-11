package az.spring.demo.springrestdemonew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class SpringRestDemoNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestDemoNewApplication.class, args);
    }

}
