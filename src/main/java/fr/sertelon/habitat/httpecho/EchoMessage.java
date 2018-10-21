package fr.sertelon.habitat.httpecho;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EchoMessage {

    @JsonProperty("message")
    public final String message;

    @JsonCreator
    public EchoMessage(@JsonProperty("message") String message) {
        this.message = message;
    }
}