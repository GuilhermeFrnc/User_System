package atv.api.apiuser.web.controller;

import atv.api.apiuser.web.controller.exception.ErrorResponse;
import atv.api.apiuser.web.dto.UserPasswordUpdateRequestDto;
import atv.api.apiuser.web.dto.UserRequestDto;
import atv.api.apiuser.web.dto.UserResponseDto;
import atv.api.apiuser.web.dto.mapper.UserMapper;
import atv.api.apiuser.entity.User;
import atv.api.apiuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api")
@Tag(name = "User Management", description = "Operations related to user registration and password management")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Operation(summary = "Register a new user", description = "Creates a new user with encrypted password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Validation error (e.g., missing fields)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "message": "Validation failed: name cannot be empty",
                                "path": "/api/users/register"
                            }
                        """))
            }),
            @ApiResponse(responseCode = "500", description = "Database connection error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "message": "An error occurred while saving the user to the database",
                                "path": "/api/users/register"
                            }
                        """))
            })
    })
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


    @Operation(summary = "Update user password", description = "Updates the password of an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Password updated successfully (No Content)"),
            @ApiResponse(responseCode = "400", description = "Invalid old password", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "message": "Invalid old password",
                                "path": "/api/users/update-password"
                            }
                        """))
            }),
            @ApiResponse(responseCode = "500", description = "Database connection error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "message": "An error occurred while updating the password in the database",
                                "path": "/api/users/update-password"
                            }
                        """))
            })
    })
    @PutMapping("/users/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody UserPasswordUpdateRequestDto passwordUpdateRequestDto){
        User user = userMapper.updateEntry(passwordUpdateRequestDto);
        userService.updatePassword(user, "UPDATE");
        return ResponseEntity.noContent().build();
    }
}
