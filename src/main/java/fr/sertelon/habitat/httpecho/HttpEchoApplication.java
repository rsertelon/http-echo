package fr.sertelon.habitat.httpecho;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HttpEchoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpEchoApplication.class, args);
	}

	@Component
	public static class JerseyConfig extends ResourceConfig {

		public JerseyConfig() {
			register(EchoResource.class);
			register(new JacksonJsonProvider());
		}
	}
}
