package project.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.services.Services;
import project.repositories.services.IServicesRepository;

import java.util.List;

@Service
public class ServicesService implements IServicesService {

    @Autowired
    private IServicesRepository iServicesRepository;

    @Override
    public void save(Services services) {
        iServicesRepository.save(services);
    }

    @Override
    public List<Services> findAllActive() {
        return iServicesRepository.findAllActive();
    }

    @Override
    public Page<Services> findAllWithNameSort(Pageable pageable) {
        return iServicesRepository.findAllWithNameSort(pageable);
    }

    @Override
    public Page<Services> findAllWithSearch(Integer serviceTypeIdVal, String convenience, String cost, Pageable pageable) {

        if (!(cost.equals("")) && serviceTypeIdVal > 0){
            return iServicesRepository.findAllWithServiceTypeAndConvenienceAndCost(serviceTypeIdVal,convenience,cost,pageable);
        }

        if (cost.equals("") && serviceTypeIdVal == 0 && convenience.equals("")){
            return iServicesRepository.findAllActivePaging(pageable);
        }

        if (serviceTypeIdVal > 0){
            return iServicesRepository.findAllWithServiceTypeAndConvenience(serviceTypeIdVal,convenience,pageable);
        }

        if (!(cost.equals(""))){
            return iServicesRepository.findAllWithConvenienceAndCost(convenience,cost,pageable);
        }

        if (!(convenience.equals(""))){
            return iServicesRepository.findAllWithConvenience(convenience,pageable);
        }
//            return findAllActive();
//        if (serviceTypeIdVal == 0){
//            return iServicesRepository.findAllWithNameAndAddressSearch(convenience,cost,pageable);
//        } else {
//            return iServicesRepository.findAllWithNameAndAddressAndPositionSearch(name,address,positionIdVal,pageable);
//        }
        return null;
    }

    @Override
    public Services findByCodeActice(String serviceCode) {
        return iServicesRepository.findByCodeActice(serviceCode);
    }
}
