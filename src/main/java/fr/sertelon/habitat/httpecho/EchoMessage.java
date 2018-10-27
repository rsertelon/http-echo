package fr.sertelon.habitat.httpecho;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EchoMessage {

    @JsonProperty("message")
    public final String message;

    @JsonProperty("sub_message")
    public final String subMessage;

    @JsonCreator
    public EchoMessage(@JsonProperty("message") String message, @JsonProperty("sub_message") String subMessage) {
        this.message = message;
        this.subMessage = subMessage;
    }
}