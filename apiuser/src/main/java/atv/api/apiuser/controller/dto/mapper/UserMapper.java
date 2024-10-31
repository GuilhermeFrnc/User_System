package atv.api.apiuser.controller.dto.mapper;

import atv.api.apiuser.controller.dto.UserRequestDto;
import atv.api.apiuser.controller.dto.UserResponseDto;
import atv.api.apiuser.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCep(userRequestDto.getCep());
        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());

        if (user.getAddress() != null) {
            UserResponseDto.AddressDto addressDto = new UserResponseDto.AddressDto();
            addressDto.setZipCode(user.getAddress().getZipCode());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setComplement(user.getAddress().getComplement());
            addressDto.setNeighborhood(user.getAddress().getNeighborhood());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            userResponseDto.setAddress(addressDto);
        }

        return userResponseDto;
    }
}
