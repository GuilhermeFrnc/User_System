package atv.api.apiuser.web.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private AddressDto address;

    @Data
    public static class AddressDto {
        private String zipCode;
        private String street;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
    }
}
