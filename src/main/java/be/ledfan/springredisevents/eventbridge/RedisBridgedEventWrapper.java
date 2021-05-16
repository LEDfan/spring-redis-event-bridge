package be.ledfan.springredisevents.eventbridge;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisBridgedEventWrapper {

    public final IBridgedEvent sharedEvent;
    public final String senderInstanceId;

    public RedisBridgedEventWrapper(IBridgedEvent sharedEvent, String senderInstanceId) {
        this.sharedEvent = sharedEvent;
        this.senderInstanceId = senderInstanceId;
    }

    public IBridgedEvent getSharedEvent() {
        return sharedEvent;
    }

    public String getSenderInstanceId() {
        return senderInstanceId;
    }

    @JsonCreator
    public static RedisBridgedEventWrapper fromJson(
            @JsonProperty(value = "sharedEvent", required = true) IBridgedEvent sharedEvent,
            @JsonProperty(value = "senderInstanceId", required = true) String senderInstanceId) {
        return new RedisBridgedEventWrapper(sharedEvent, senderInstanceId);
    }
}
