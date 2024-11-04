package atv.api.apiuser.web.controller;

import atv.api.apiuser.web.dto.UserPasswordUpdateRequestDto;
import atv.api.apiuser.web.dto.UserRequestDto;
import atv.api.apiuser.web.dto.UserResponseDto;
import atv.api.apiuser.web.dto.mapper.UserMapper;
import atv.api.apiuser.entity.User;
import atv.api.apiuser.service.UserService;
import jakarta.validation.Valid;
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

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/users/register")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        User savedUser = userService.createUser(user, "CREATE");
        UserResponseDto userResponseDto = userMapper.toResponseDto(savedUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(userResponseDto);
    }

    @PutMapping("/users/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody UserPasswordUpdateRequestDto passwordUpdateRequestDto){
        User user = userMapper.updateEntry(passwordUpdateRequestDto);
        userService.updatePassword(user);
        return ResponseEntity.noContent().build();
    }
}
