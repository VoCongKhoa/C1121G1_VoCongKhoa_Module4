package project.services.services;

import project.models.services.RentType;

import java.util.List;

public interface IRentTypeService {
    List<RentType> findAllActive();
}
