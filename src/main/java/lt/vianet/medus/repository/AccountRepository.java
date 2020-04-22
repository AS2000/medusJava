package lt.vianet.medus.repository;

import lt.vianet.medus.model.Account;
import lt.vianet.medus.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Collection<Account> findByCustomer(Customer customer);
}
