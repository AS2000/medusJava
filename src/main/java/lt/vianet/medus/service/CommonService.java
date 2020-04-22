package lt.vianet.medus.service;

import lt.vianet.medus.model.OfferedService;
import lt.vianet.medus.repository.OfferedServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommonService {

    @Autowired
    OfferedServiceRepository offeredServiceRepository;

    public Collection<OfferedService> getOfferedServices() {
        return offeredServiceRepository.findAll();
    }
}

