package project.services.services;

import project.models.services.ServiceType;

import java.util.List;

public interface IServiceTypeService {
    List<ServiceType> findAllActive();
}
