package project.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.services.ServiceType;
import project.repositories.services.IServiceTypeRepository;

import java.util.List;

@Service
public class ServiceTypeService implements IServiceTypeService {

    @Autowired
    private IServiceTypeRepository iServiceTypeRepository;

    @Override
    public List<ServiceType> findAllActive() {
        return iServiceTypeRepository.findAllActive();
    }
}
