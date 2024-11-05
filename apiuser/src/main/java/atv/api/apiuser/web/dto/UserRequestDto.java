package atv.api.apiuser.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "Name cannot be empty")@NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 200, message = "Password must be less than 20 characters")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50, message = "Email must be less than 50 characters")
    private String email;

    @NotBlank(message = "CEP cannot be empty")
    @Size(max = 8, message = "CEP must be less than 8 characters")
    private String cep;
}
