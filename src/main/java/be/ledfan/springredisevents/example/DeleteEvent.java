package be.ledfan.springredisevents.example;

import be.ledfan.springredisevents.eventbridge.AbstractBridgedEvent;
import be.ledfan.springredisevents.eventbridge.RedisEventBridge;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteEvent extends AbstractBridgedEvent {

    private final int id;

    public DeleteEvent(Object source, int id) {
        super(source);
        this.id = id;
    }

    @JsonCreator
    public static DeleteEvent fromJson(
            @JsonProperty(value = "id", required = true) int id) {
        return new DeleteEvent(RedisEventBridge.eventSource, id);
    }

    public int getId() {
        return id;
    }

}