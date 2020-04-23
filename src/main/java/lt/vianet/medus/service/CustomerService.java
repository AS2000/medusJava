package lt.vianet.medus.service;

import lt.vianet.medus.exception.ExceptionUtils;
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
import lt.vianet.medus.util.CreateReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
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
    @Autowired
    ExceptionUtils exceptionUtils;
    @Autowired
    CreateReturnMessage createReturnMessage;

    @PersistenceContext
    private EntityManager entityManager;

    public Customer createCustomer(final Customer customer) {
        customerRepository.findAll().forEach(el -> {
            if (el.getPersonalCode() == customer.getPersonalCode()) {
                throw new ResourceNotFoundException("Customer with this Personal Code already exists: " + customer.getPersonalCode());
            }
        });
        return customerRepository.save(
                Customer.builder()
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .personalCode(customer.getPersonalCode())
                        .address(customer.getAddress())
                        .companyName(customer.getCompanyName())
                        .companyCode(customer.getCompanyCode())
                        .build()
        );
    }

    public Customer updateCustomerInfo(final Customer updateCustomerData) {
        if (exceptionUtils.isCustomerOrThrowException(updateCustomerData.getId())) {
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
        if (exceptionUtils.isCustomerOrThrowException(customerId)) {
            customerRepository.deleteById(customerId);
            return createReturnMessage.createReturnMessage("User was deleted");
        }
        return createReturnMessage.createReturnMessage("User was not deleted");
    }

    public Customer getCustomerInfo(final Long customerId) {
        if (exceptionUtils.isCustomerOrThrowException(customerId)) {
            return customerRepository
                    .findById(customerId)
                    .get();
        }
        return null;
    }

    public Collection<OrderedService> getCustomerServices(final Long customerId) {
        if (exceptionUtils.isCustomerOrThrowException(customerId)) {
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
        if (exceptionUtils.isCustomerOrThrowException(customerId)) {
            Customer customer = customerRepository.findById(customerId).get();
            orderedServices.forEach(
                    (service) -> {
                        service.setCustomer(customer);
                        orderedServiceRepository.save(service);
                    });
            return createReturnMessage.createReturnMessage("User Services was Updated");
        }
        return createReturnMessage.createReturnMessage("User Services was not Updated");
    }


    public OrderedService getOrderedService(final Long serviceId) {
        if (exceptionUtils.isServiceOrThrowException(serviceId)) {
            return orderedServiceRepository
                    .findById(serviceId)
                    .get();
        }
        return null;
    }

    public Collection<Account> getCustomerAccounts(final Long customerId) {
        if (exceptionUtils.isCustomerOrThrowException(customerId)) {
            return accountRepository.
                    findByCustomer(
                            customerRepository
                                    .findById(customerId)
                                    .get()
                    );
        }
        return null;
    }


    public Account getAccount(final Long accountId) {
        if (exceptionUtils.isAccountOrThrowException(accountId)) {
            return accountRepository
                    .findById(accountId)
                    .get();
        }
        return null;
    }

    public Collection<PhoneNumber> getCustomerMsisdn(final Long customerId, final Long accountId) {
        if (
                exceptionUtils.isCustomerOrThrowException(customerId) &&
                        exceptionUtils.isAccountOrThrowException(accountId)
        ) {
            Account account = accountRepository.findById(accountId).get();
            if (customerId == account.getCustomer().getId()) {
                Msisdn msisdn = msisdnRepository.findById(accountId).get();
                return phoneNumberRepository.findByMsisdn(msisdn);
            }
        }
        return null;
    }


    public Collection<PhoneNumber> getPhoneNumber(final Long msisdnId) {
        if (exceptionUtils.isMsisdnOrThrowException(msisdnId) &&
                exceptionUtils.isPhoneNumberOrThrowException(msisdnId)
        ) {
            return phoneNumberRepository
                    .findByMsisdn(
                            msisdnRepository
                                    .findById(msisdnId)
                                    .get());
        }
        return null;
    }

    public Collection<Customer> getAllCustomersInfo() {
        return customerRepository.findAll();
    }
}
