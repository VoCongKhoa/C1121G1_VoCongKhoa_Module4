package project.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.services.RentType;
import project.repositories.services.IRentTypeRepository;

import java.util.List;

@Service
public class RentTypeService implements IRentTypeService{

    @Autowired
    private IRentTypeRepository iRentTypeRepository;

    @Override
    public List<RentType> findAllActive() {
        return iRentTypeRepository.findAllActive();
    }
}
