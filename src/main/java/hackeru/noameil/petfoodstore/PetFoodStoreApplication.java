package hackeru.noameil.petfoodstore;

import hackeru.noameil.petfoodstore.config.JWTConfig;
import hackeru.noameil.petfoodstore.error.PetFoodStoreException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//generated security password: 064f3eea-4ecd-44e3-ac55-134aa65b10e5

@SpringBootApplication
@EnableConfigurationProperties(JWTConfig.class )
public class PetFoodStoreApplication {

    public static void main(String[] args) {

        //catch all my exceptions
        try {
            SpringApplication.run(PetFoodStoreApplication.class, args);
        } catch (PetFoodStoreException b) {
            //send to log server
        }
    }

}
