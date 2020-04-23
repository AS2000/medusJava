package lt.vianet.medus.hateoas_resource;

import lombok.Getter;
import lt.vianet.medus.controller.CustomerController;
import lt.vianet.medus.model.OrderedService;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class OrderedServiceResource extends ResourceSupport {

    private final OrderedService orderedService;

    public OrderedServiceResource(final OrderedService orderedService) {
        this.orderedService = orderedService;
        add(
                linkTo(
                        methodOn(CustomerController.class)
                                .getOrderedService(orderedService.getId())
                ).withSelfRel());
    }
}
