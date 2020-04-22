package lt.vianet.medus.service;

import lt.vianet.medus.model.Account;
import lt.vianet.medus.model.Customer;
import lt.vianet.medus.model.Msisdn;
import lt.vianet.medus.model.OrderedService;
import lt.vianet.medus.model.PhoneNumber;
import lt.vianet.medus.repository.AccountRepository;
import lt.vianet.medus.repository.MsisdnRepository;
import lt.vianet.medus.repository.OrderedServiceRepository;
import lt.vianet.medus.repository.CustomerRepository;
import lt.vianet.medus.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedServiceRepository orderedServiceRepository;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PhoneNumberRepository phoneNumberRepository;
    @Autowired
    MsisdnRepository msisdnRepository;

    public Customer createCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomerInfo(final Customer updateCustomerData) {
        if (isCustomerOrThrowException(updateCustomerData.getId())) {
            Customer customer = customerRepository.findById(updateCustomerData.getId()).get();

            customer.setName(updateCustomerData.getName());
            customer.setSurname(updateCustomerData.getSurname());
            customer.setAddress(updateCustomerData.getAddress());
            customer.setCompanyCode(updateCustomerData.getCompanyCode());
            customer.setCompanyName(updateCustomerData.getCompanyName());

            return customerRepository.save(customer);
        }
        return null;
    }

    public Map<String, Boolean> deleteCustomer(final Long customerId) {
        if (isCustomerOrThrowException(customerId)) {
            customerRepository.deleteById(customerId);
            return createReturnMessage("User was deleted");
        }
        return createReturnMessage("User was not deleted");
    }

    public Customer getCustomerInfo(final Long customerId) {
        if (isCustomerOrThrowException(customerId)) {
            return customerRepository
                    .findById(customerId)
                    .get();
        }
        return null;
    }

    public Collection<OrderedService> getCustomerServices(final Long customerId) {
        if (isCustomerOrThrowException(customerId)) {
            return orderedServiceRepository.
                    findByCustomer(
                            customerRepository
                                    .findById(customerId)
                                    .get()
                    );
        }
        return null;
    }

    public Map updateCustomerServices(final Long customerId, final Collection<OrderedService> orderedServices) {
        if (isCustomerOrThrowException(customerId)) {
            Customer customer = customerRepository.findById(customerId).get();
            orderedServices.forEach(
                    (service) -> {
                        service.setCustomer(customer);
                        orderedServiceRepository.save(service);
                    });
            return createReturnMessage("User Services was Updated");
        }
        return createReturnMessage("User Services was not Updated");
    }


    public Collection<Account> getCustomerAccounts(final Long customerId) {
        if (isCustomerOrThrowException(customerId)) {
            return accountRepository.
                    findByCustomer(
                            customerRepository
                                    .findById(customerId)
                                    .get()
                    );
        }
        return null;
    }

    public Collection<PhoneNumber> getCustomerMsisdn(final Long customerId, final Long accountId) {
        if (
                isCustomerOrThrowException(customerId) &&
                        isAccountOrThrowException(accountId)
        ) {
            Account account = accountRepository.findById(accountId).get();
            if (customerId == account.getCustomer().getId()) {
                Msisdn msisdn = msisdnRepository.findById(accountId).get();
                return phoneNumberRepository.findByMsisdn(msisdn);
            }
        }
        return null;
    }


    public Collection<Customer> getAllCustomersInfo() {
        return customerRepository.findAll();
    }


    private Boolean isCustomerOrThrowException(Long customerId) throws ResourceNotFoundException {
        if (null != customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer was not found for this id:" + customerId)
                )) return true;
        return false;
    }

    private Boolean isAccountOrThrowException(Long accountId) throws ResourceNotFoundException {
        if (null != accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account was not found for this id:" + accountId)
                )) return true;
        return false;
    }

    private Map createReturnMessage(String text) {
        Map<String, Boolean> message = new HashMap<>();
        message.put(text, Boolean.TRUE);
        return message;
    }

}
