package lt.vianet.medus.repository;

import lt.vianet.medus.model.Msisdn;
import lt.vianet.medus.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    Collection<PhoneNumber> findByMsisdn(Msisdn msisdn);
}
