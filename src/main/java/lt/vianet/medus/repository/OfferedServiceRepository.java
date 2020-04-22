package lt.vianet.medus.repository;

import lt.vianet.medus.model.OfferedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedServiceRepository extends JpaRepository<OfferedService, Long> {
}
