package atv.api.apiuser.controller;

import atv.api.apiuser.controller.dto.UserRequestDto;
import atv.api.apiuser.controller.dto.UserResponseDto;
import atv.api.apiuser.controller.dto.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);

        User savedUser = userService.createUser(user, "CREATE");
        UserResponseDto userResponseDto = userMapper.toResponseDto(savedUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(userResponseDto);
    }
}
