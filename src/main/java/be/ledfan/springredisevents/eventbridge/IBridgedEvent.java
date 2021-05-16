package be.ledfan.springredisevents.eventbridge;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.MINIMAL_CLASS,
        property = "@class"
)
public interface IBridgedEvent {

    boolean getExternal();

    void setExternal(boolean external);

}
