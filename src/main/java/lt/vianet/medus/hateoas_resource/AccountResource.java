package lt.vianet.medus.hateoas_resource;

import lombok.Getter;
import lt.vianet.medus.controller.CustomerController;
import lt.vianet.medus.model.Account;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class AccountResource extends ResourceSupport {

    private Account account;

    public AccountResource(final Account account) {
        this.account = account;
        add(
                linkTo(
                        methodOn(CustomerController.class)
                                .getAccount(account.getId())
                ).withSelfRel());
    }
}
