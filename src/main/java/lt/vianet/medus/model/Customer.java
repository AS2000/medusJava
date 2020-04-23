package lt.vianet.medus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Please provide a name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Please provide a surname")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_code")
    private Long companyCode;

    @Column(name = "personal_code")
    private Long personalCode;

    @Column(name = "address")
    private String address;
}
