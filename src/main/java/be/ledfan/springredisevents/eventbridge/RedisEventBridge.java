package be.ledfan.springredisevents.eventbridge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RedisEventBridge implements MessageListener {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ChannelTopic channelTopic;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final String instanceId;

    private final ObjectMapper objectMapper;

    public RedisEventBridge(RedisTemplate<String, Object> redisTemplate, ChannelTopic channelTopic, ApplicationEventPublisher applicationEventPublisher) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
        this.applicationEventPublisher = applicationEventPublisher;
        this.instanceId = java.util.UUID.randomUUID().toString();
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    }

    @EventListener
    public void onGenerateEvent(IBridgedEvent event) {
        if (event.getExternal()) {
            return;
        }
        RedisBridgedEventWrapper redisEventWrapper = new RedisBridgedEventWrapper(event, instanceId);
        redisTemplate.convertAndSend(channelTopic.getTopic(), redisEventWrapper);
    }

    public void onMessage(Message message, byte[] pattern) {

        try {
            RedisBridgedEventWrapper eventWrapper = objectMapper.readValue(message.getBody(), RedisBridgedEventWrapper.class);

            System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));

            if (!eventWrapper.getSenderInstanceId().equals(instanceId)) {
                IBridgedEvent sharedEvent = eventWrapper.getSharedEvent();
                sharedEvent.setExternal(true);
                applicationEventPublisher.publishEvent(sharedEvent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class EventSource {
    }

    public static EventSource eventSource = new EventSource();

}
