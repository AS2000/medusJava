package lt.vianet.medus.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data
@Table(name = "phone_numbers")
@Entity
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code", nullable = false)
    private Integer countryCode;

    @Column(name = "ph_number", nullable = false)
    private Long phNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="msisdn_id")
    private Msisdn msisdn;
}
