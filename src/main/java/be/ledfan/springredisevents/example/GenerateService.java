package be.ledfan.springredisevents.example;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GenerateService {

    private final ConcurrentLinkedQueue<Integer> generated = new ConcurrentLinkedQueue<>();

    private final ApplicationEventPublisher applicationEventPublisher;

    public GenerateService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public int generate() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);

        generated.add(randomNum);

        applicationEventPublisher.publishEvent(new GenerateEvent(this.getClass().getSimpleName(), randomNum));

        return randomNum;
    }

    public void delete(int id) {
        generated.remove(id);

        applicationEventPublisher.publishEvent(new DeleteEvent(this.getClass().getSimpleName(), id));
    }

    @EventListener
    public void onGenerateEvent(GenerateEvent generateEvent) {
        if (generateEvent.getExternal()) {
            generated.add(generateEvent.getId());
        }
    }

    @EventListener
    public void onDeleteEvent(DeleteEvent deleteEvent) {
        if (deleteEvent.getExternal()) {
            generated.remove(deleteEvent.getId());
        }
    }

}
