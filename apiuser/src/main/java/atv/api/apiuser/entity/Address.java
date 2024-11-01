package atv.api.apiuser.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code")
    @JsonAlias("cep")
    private String zipCode;

    @Column(name = "street")
    @JsonAlias("logradouro")
    private String street;

    @Column(name = "complement")
    @JsonAlias("complemento")
    private String complement;

    @Column(name = "neighborhood")
    @JsonAlias("bairro")
    private String neighborhood;

    @Column(name = "city")
    @JsonAlias("localidade")
    private String city;

    @Column(name = "state")
    @JsonAlias("uf")
    private String state;
}
