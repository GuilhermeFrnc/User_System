package atv.api.apiuser.controller.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String password;
    private String email;
    private String cep;
}
