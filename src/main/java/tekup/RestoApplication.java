package tekup;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoApplication.class, args);
		
	}
@Bean
public ModelMapper name() {
	
	return new ModelMapper();
}
}
