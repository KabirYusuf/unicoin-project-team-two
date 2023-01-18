package africa.semicolon.unicoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UnicoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicoinApplication.class, args);
	}

}
