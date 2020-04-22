package lt.vianet.medus.repository;

import lt.vianet.medus.model.Msisdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsisdnRepository extends JpaRepository<Msisdn, Long> {
}
