package be.ledfan.springredisevents.eventbridge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.ApplicationEvent;

@JsonIgnoreProperties({"source", "external"})
public class AbstractBridgedEvent extends ApplicationEvent implements IBridgedEvent {

    private boolean external = false;

    public AbstractBridgedEvent(Object source) {
        super(source);
    }

    @Override
    public boolean getExternal() {
        return external;
    }

    @Override
    public void setExternal(boolean external) {
        this.external = external;
    }
}
