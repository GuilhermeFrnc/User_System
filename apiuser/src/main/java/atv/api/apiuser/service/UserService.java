package atv.api.apiuser.service;

import atv.api.apiuser.entity.Address;
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
    private final AddressService addressService;

    @Value("${topic.message-action-user}")
    private String actionUser;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public UserService(AddressService addressService) {
        this.addressService = addressService;
    }

    public User createUser(User user, String action){
        Address address = findAddress(user.getCep());
        user.setAddress(address);
        sendMessage(user.getName(), action);
        return user;
    }


    public void sendMessage(String name, String action) {
        String message =  "Name: "+ name + " Action: " + action;
        logger.info("Message -> {}", message);
        this.kafkaTemplate.send(actionUser, message);
    }


    public Address findAddress(String cep) {
        return addressService.getAddressByCep(cep);
    }
}

