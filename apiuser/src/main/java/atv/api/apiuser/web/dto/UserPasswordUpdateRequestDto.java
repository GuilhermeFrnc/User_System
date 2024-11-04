package atv.api.apiuser.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPasswordUpdateRequestDto {
    private String name;

    private String oldPassword;

    private String newPassword;
}
