package dummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = JacksonAutoConfiguration.class)
public class ArtistsApplication {

	public static void main(String[] args) { 
		SpringApplication.run(ArtistsApplication.class, args);
	}

}
