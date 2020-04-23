package lt.vianet.medus.hateoas_resource;

import lombok.Getter;
import lt.vianet.medus.controller.CustomerController;
import lt.vianet.medus.model.Customer;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class CustomerResource extends ResourceSupport {

    private Customer customer;

    public CustomerResource(final Customer customer) {
        this.customer = customer;
        add(
                linkTo(
                        methodOn(CustomerController.class)
                                .getCustomerInfo(customer.getId())
                ).withSelfRel());
    }
}
