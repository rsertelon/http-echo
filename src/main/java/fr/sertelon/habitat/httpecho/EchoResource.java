package fr.sertelon.habitat.httpecho;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.exceptions.JadeCompilerException;
import de.neuland.jade4j.template.ClasspathTemplateLoader;
import de.neuland.jade4j.template.JadeTemplate;

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

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessageAsHtml() throws JadeCompilerException, IOException {
        JadeConfiguration config = new JadeConfiguration();
        config.setTemplateLoader(new ClasspathTemplateLoader());
        JadeTemplate template = config.getTemplate("index");

        Map<String, Object> model = new HashMap<>();
        model.put("text", text);

        return config.renderTemplate(template, model);
    }

    /* Static resources for nice habitat page */
    @GET
    @Path("/{path:.*}")
    public Response getStaticResource(@PathParam("path") String path) {
        InputStream fileContent = Thread.currentThread().getContextClassLoader().getResourceAsStream("public/" + path);
        if (fileContent == null) {
            return Response.status(Status.NOT_FOUND).build();
        } else {
            String type = MediaType.APPLICATION_OCTET_STREAM;
            if (path.endsWith(".svg")) {
                type = "image/svg+xml";
            } else if (path.endsWith(".css")) {
                type = "text/css";
            }

            return Response.status(Status.OK).entity(fileContent).type(type).build();
        }
    }
}