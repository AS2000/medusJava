package lt.vianet.medus.exception;

import lt.vianet.medus.repository.AccountRepository;
import lt.vianet.medus.repository.CustomerRepository;
import lt.vianet.medus.repository.MsisdnRepository;
import lt.vianet.medus.repository.OrderedServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtils {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedServiceRepository orderedServiceRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MsisdnRepository msisdnRepository;

    public Boolean isCustomerOrThrowException(Long customerId) throws org.springframework.data.rest.webmvc.ResourceNotFoundException {
        if (null != customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new org.springframework.data.rest.webmvc.ResourceNotFoundException("Customer was not found for this id: " + customerId)
                )) return true;
        return false;
    }

    public Boolean isAccountOrThrowException(Long accountId) throws org.springframework.data.rest.webmvc.ResourceNotFoundException {
        if (null != accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new org.springframework.data.rest.webmvc.ResourceNotFoundException("Account was not found for this id: " + accountId)
                )) return true;
        return false;
    }

    public Boolean isServiceOrThrowException(Long serviceId) throws org.springframework.data.rest.webmvc.ResourceNotFoundException {
        if (null != orderedServiceRepository.findById(serviceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Service was not found for this id: " + serviceId)
                )) return true;
        return false;
    }

    public Boolean isMsisdnOrThrowException(Long msisdnId) throws org.springframework.data.rest.webmvc.ResourceNotFoundException {
        if (null != msisdnRepository.findById(msisdnId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("MSISDN was not found for this id: " + msisdnId)
                )) return true;
        return false;
    }

    public Boolean isPhoneNumberOrThrowException(Long msisdnId) throws org.springframework.data.rest.webmvc.ResourceNotFoundException {
        if (null != msisdnRepository.findById(msisdnId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("MSISDN was not found for this id: " + msisdnId)
                )) return true;
        return false;
    }
}
