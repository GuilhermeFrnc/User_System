package atv.api.apiuser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50, message = "Email must be less than 50 characters")
    @Column(nullable = false, length = 50)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 200, message = "Password must be less than 20 characters")
    @Column(nullable = false, length = 200)
    private String password;

    @NotBlank(message = "CEP cannot be empty")
    @Size(max = 50, message = "CEP must be less than 50 characters")
    @Column(nullable = false, length = 50)
    private String cep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
