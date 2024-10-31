package atv.api.apiuser.controller;

import atv.api.apiuser.entity.User;
import atv.api.apiuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User savedUser = userService.createUser(user, "CREATE");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }
}
