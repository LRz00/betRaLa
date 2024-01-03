package br.com.lrz.betRaLa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BetRaLaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
                new SpringApplicationBuilder(BetRaLaApplication.class).headless(false).run(args);
        
	}

}
