package atv.api.apiuser.web.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String password;
    private String email;
    private String cep;
}
