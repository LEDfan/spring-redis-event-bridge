package be.ledfan.springredisevents.example;

import be.ledfan.springredisevents.eventbridge.AbstractBridgedEvent;
import be.ledfan.springredisevents.eventbridge.RedisEventBridge;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateEvent extends AbstractBridgedEvent {

    private final int id;

    public GenerateEvent(Object source, int id) {
        super(source);
        this.id = id;
    }

    @JsonCreator
    public static GenerateEvent fromJson(
            @JsonProperty(value = "id", required = true) int id) {
        return new GenerateEvent(RedisEventBridge.eventSource, id);
    }

    public int getId() {
        return id;
    }

}