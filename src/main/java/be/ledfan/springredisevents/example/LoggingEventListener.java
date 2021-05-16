package be.ledfan.springredisevents.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class LoggingEventListener {

    @EventListener
    public void onGenerateEvent(GenerateEvent generateEvent) {
        System.out.printf("On Generate Event, %s\n",  generateEvent.getId());
    }

    @EventListener
    public void onDeleteEvent(DeleteEvent deleteEvent) {
        System.out.printf("On Delete Event, %s\n",  deleteEvent.getId());
    }


}
