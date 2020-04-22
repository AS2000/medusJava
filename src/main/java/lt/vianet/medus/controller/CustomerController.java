package lt.vianet.medus.controller;

import lt.vianet.medus.model.Account;
import lt.vianet.medus.model.OrderedService;
import lt.vianet.medus.model.Customer;
import lt.vianet.medus.model.PhoneNumber;
import lt.vianet.medus.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

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

    @RequestMapping(
            value = UrlConst.GET_CUSTOMER_SERVICES,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<OrderedService> getCustomerServices(@RequestParam Long customerId) {
        return customerService.getCustomerServices(customerId);
    }

    @RequestMapping(
            value = UrlConst.UPDATE_CUSTOMER_SERVICES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map updateCustomerServices(@RequestParam Long customerId, @RequestBody @Valid Collection<OrderedService> orderedServices) {
        return customerService.updateCustomerServices(customerId, orderedServices);
    }

    @RequestMapping(
            value = UrlConst.GET_CUSTOMER_ACCOUNTS,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Account> getCustomerAccounts(@RequestParam Long customerId) {
        return customerService.getCustomerAccounts(customerId);
    }

    @RequestMapping(
            value = UrlConst.GET_CUSTOMER_MSISDN,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PhoneNumber> getCustomerMsisdn(@RequestParam Long customerId, @RequestParam Long accountId) {
        return customerService.getCustomerMsisdn(customerId, accountId);
    }





    // (TODO) do not use it in prod because of GRPR :)
    @RequestMapping(
            value = UrlConst.GET_ALL_CUSTOMERS_INFO,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Customer> getAllCustomersInfo() {
        return customerService.getAllCustomersInfo();
    }

}
