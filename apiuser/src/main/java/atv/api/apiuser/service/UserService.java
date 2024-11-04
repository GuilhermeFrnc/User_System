package atv.api.apiuser.service;

import atv.api.apiuser.entity.Address;
import atv.api.apiuser.entity.User;
import atv.api.apiuser.exception.DatabaseException;
import atv.api.apiuser.exception.InvalidPasswordException;
import atv.api.apiuser.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final AddressService addressService;

    @Value("${topic.message-action-user}")
    private String actionUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserService(AddressService addressService) {
        this.addressService = addressService;
    }


    public User createUser(User user, String action) {
        try {
            Address address = findAddress(user.getCep());
            user.setAddress(address);
            sendMessage(user.getName(), action);
            User savedUser = userRepository.save(user);
            logger.info("User saved: {}", savedUser);
            return savedUser;
        } catch (Exception e) {
            throw new DatabaseException("An error occurred while saving the user to the database", e);
        }
    }

    public void sendMessage(String name, String action) {
        String message =  "Name: "+ name + " Action: " + action;
        logger.info("Message -> {}", message);
        this.kafkaTemplate.send(actionUser, message);
    }


    public Address findAddress(String cep) {
        return addressService.getAddressByCep(cep);
    }


    public Void updatePassword(User user){
        Optional<User> optionalUser = userRepository.findByNameAndPassword(user.getName(), user.getOldPassword());

        if (!optionalUser.isPresent()) {
            throw new InvalidPasswordException("Usuário não encontrado ou senha antiga incorreta.");
        }

        User existingUser = optionalUser.get();
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return null;
    }
}

