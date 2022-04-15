package project.services;

import project.models.Smartphone;

import java.util.List;
import java.util.Optional;

public interface ISmartphoneService {
    Smartphone save(Smartphone smartphone);

    List<Smartphone> findAll();


    Optional<Smartphone> findById(Long id);

    void remove(Long id);
}
