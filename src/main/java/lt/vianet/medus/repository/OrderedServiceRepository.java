package lt.vianet.medus.repository;

import lt.vianet.medus.model.OrderedService;
import lt.vianet.medus.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderedServiceRepository extends JpaRepository<OrderedService, Long> {
    Collection<OrderedService> findByCustomer(Customer customer);
}
