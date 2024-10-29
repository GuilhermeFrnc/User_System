package atv.api.apiuser.service;

import atv.api.apiuser.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${topic.message-action-user}")
    private String actionUser;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(User user, String action) {
        String message =  "Name: "+ user.getName() + " Action: " + action;
        logger.info("Message -> {}", message);
        this.kafkaTemplate.send(actionUser, message);
    }
}

