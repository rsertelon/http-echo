package fr.sertelon.habitat.httpecho;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Path("/")
@Component
public class EchoResource {

    @Value("${app.text}")
    private String text;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EchoMessage getMessage() {
        return new EchoMessage(text);
    }
}