package lt.vianet.medus.controller;

import lt.vianet.medus.model.OfferedService;
import lt.vianet.medus.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping(value = UrlConst.MEDUS)
@RestController
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(
            value = UrlConst.GET_OFFERED_SERVICES,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<OfferedService> getOfferedServices() {
        return commonService.getOfferedServices();
    }
}
