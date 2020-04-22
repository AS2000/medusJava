package lt.vianet.medus;

import lt.vianet.medus.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static lt.vianet.medus.controller.UrlConst.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + MEDUS;
    }

    @Test
    public void contextLoads() {
    }


    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setSurname("Test");
        customer.setPersonalCode(38801010011L);
        ResponseEntity<Customer> postResponse = restTemplate
                .postForEntity(getRootUrl() + CREATE_CUSTOMER, customer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetAllCustomersInfo() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + GET_ALL_CUSTOMERS_INFO,
                HttpMethod.POST, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateCustomerInfo() {
        int id = 1;
        Customer customer = restTemplate.getForObject(getRootUrl() + UPDATE_CUSTOMER_INFO + '/' + id, Customer.class);
        customer.setName("Test1");
        customer.setSurname("Test2");
        Customer updatedCustomer = restTemplate.getForObject(getRootUrl() + UPDATE_CUSTOMER_INFO + '/' + id, Customer.class);
        assertNotNull(updatedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        int id = 2;
        Customer customer = restTemplate.getForObject(getRootUrl() + UPDATE_CUSTOMER_INFO + '/' + id, Customer.class);
        assertNotNull(customer);
        restTemplate.delete(getRootUrl() + DELETE_CUSTOMER + '/' + id);
        try {
            restTemplate.getForObject(getRootUrl() + UPDATE_CUSTOMER_INFO + '/' + id, Customer.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}