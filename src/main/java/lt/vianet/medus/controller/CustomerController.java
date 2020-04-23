package lt.vianet.medus.controller;

import lt.vianet.medus.hateoas_resource.AccountResource;
import lt.vianet.medus.hateoas_resource.CustomerResource;
import lt.vianet.medus.hateoas_resource.PhoneNumberResource;
import lt.vianet.medus.hateoas_resource.OrderedServiceResource;
import lt.vianet.medus.model.Account;
import lt.vianet.medus.model.OrderedService;
import lt.vianet.medus.model.Customer;
import lt.vianet.medus.model.PhoneNumber;
import lt.vianet.medus.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping(value = UrlConst.MEDUS)
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(
            value = UrlConst.CREATE_CUSTOMER,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        return customerService.createCustomer(customer);
    }

    @RequestMapping(
            value = UrlConst.UPDATE_CUSTOMER_INFO,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateCustomerInfo(@RequestBody @Valid Customer customer) {
        return customerService.updateCustomerInfo(customer);
    }

    @RequestMapping(
            value = UrlConst.DELETE_CUSTOMER,
            method = RequestMethod.POST)
    public Map<String, Boolean> deleteCustomer(@RequestParam Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @RequestMapping(
            value = UrlConst.GET_CUSTOMER_INFO,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerInfo(@RequestParam Long customerId) {
        return customerService.getCustomerInfo(customerId);
    }

    @PostMapping(
            value = UrlConst.GET_CUSTOMER_SERVICES,
            produces = "application/hal+json")
    public Collection<OrderedServiceResource> getCustomerServices(@RequestParam Long customerId) {
        Collection<OrderedService> orderedServices = customerService.getCustomerServices(customerId);

        Collection<OrderedServiceResource> resourceList = orderedServices
                .stream()
                .map(OrderedServiceResource::new)
                .collect(Collectors.toList());
        return resourceList;
    }

    @RequestMapping(
            value = UrlConst.GET_ORDERED_SERVICE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderedService getOrderedService(@RequestParam Long serviceId) {
        return customerService.getOrderedService(serviceId);
    }

    @RequestMapping(
            value = UrlConst.UPDATE_CUSTOMER_SERVICES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map updateCustomerServices(@RequestParam Long customerId, @RequestBody @Valid Collection<OrderedService> orderedServices) {
        return customerService.updateCustomerServices(customerId, orderedServices);
    }

    @PostMapping(
            value = UrlConst.GET_CUSTOMER_ACCOUNTS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<AccountResource> getCustomerAccounts(@RequestParam Long customerId) {
        Collection<Account> accounts = customerService.getCustomerAccounts(customerId);

        Collection<AccountResource> resourceList = accounts
                .stream()
                .map(AccountResource::new)
                .collect(Collectors.toList());
        return resourceList;
    }

    @RequestMapping(
            value = UrlConst.GET_ACCOUNT,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@RequestParam Long accountId) {
        return customerService.getAccount(accountId);
    }

    @PostMapping(
            value = UrlConst.GET_CUSTOMER_MSISDN,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PhoneNumberResource> getCustomerMsisdn(@RequestParam Long customerId, @RequestParam Long accountId) {

        Collection<PhoneNumber> phoneNumbers = customerService.getCustomerMsisdn(customerId, accountId);

        Collection<PhoneNumberResource> resourceList = phoneNumbers
                .stream()
                .map(PhoneNumberResource::new)
                .collect(Collectors.toList());
        return resourceList;
    }

    @RequestMapping(
            value = UrlConst.GET_PHONE_NUMBER,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PhoneNumber> getMsisdn(@RequestParam Long msisdnId) {
        return customerService.getPhoneNumber(msisdnId);
    }

    @PostMapping(
            value = UrlConst.GET_ALL_CUSTOMERS_INFO,
            produces = "application/hal+json")
    public Collection<CustomerResource> getAllCustomersInfo() {

        Collection<Customer> customers = customerService.getAllCustomersInfo();

        Collection<CustomerResource> resourceList = customers
                .stream()
                .map(CustomerResource::new)
                .collect(Collectors.toList());
        return resourceList;
    }
}