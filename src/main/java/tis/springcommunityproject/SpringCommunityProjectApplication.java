package tis.springcommunityproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class SpringCommunityProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCommunityProjectApplication.class, args);
	}

}
