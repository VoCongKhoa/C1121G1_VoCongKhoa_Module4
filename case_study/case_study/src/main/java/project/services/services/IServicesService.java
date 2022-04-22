package project.services.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.services.Services;

import java.util.List;

public interface IServicesService {

    void save(Services services);

    List<Services> findAllActive();

    Page<Services> findAllWithNameSort(Pageable pageable);

    Page<Services> findAllWithSearch(Integer serviceTypeIdVal, String convenience, String cost, Pageable pageable);

    Services findByCodeActice(String serviceCode);

    Page<Services> findAllWithCostSort(Pageable pageable);

    Page<Services> findAllWithAreaSort(Pageable pageable);

}
