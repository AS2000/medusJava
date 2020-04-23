package lt.vianet.medus.hateoas_resource;

import lombok.Getter;
import lt.vianet.medus.controller.CustomerController;
import lt.vianet.medus.model.PhoneNumber;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class PhoneNumberResource extends ResourceSupport {

    private PhoneNumber phoneNumber;

    public PhoneNumberResource(final PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        add(
                linkTo(
                        methodOn(CustomerController.class)
                                .getMsisdn(phoneNumber.getId())
                ).withSelfRel());
    }
}
