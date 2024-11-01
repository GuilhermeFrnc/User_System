package atv.api.notify.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Notify {
    private static final Logger logger = LoggerFactory.getLogger(Notify.class);

    @KafkaListener(topics = "${topic.message-action-user}", groupId = "group_id")
    public void notify (String message) throws IOException{
        logger.info(String.format("### -> message -> %s", message));
    }
}
