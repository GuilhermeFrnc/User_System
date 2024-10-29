package atv.api.apiuser.controller;

import atv.api.apiuser.entity.User;
import atv.api.apiuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> sendMessage(@RequestBody User user){
        userService.sendMessage(user, "POST");
        return ResponseEntity.ok().body(user);
    }
}
