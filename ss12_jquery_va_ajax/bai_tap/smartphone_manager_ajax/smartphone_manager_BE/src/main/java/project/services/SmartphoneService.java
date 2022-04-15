package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Smartphone;
import project.repositories.ISmartphoneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SmartphoneService implements ISmartphoneService{
    @Autowired
    ISmartphoneRepository iSmartphoneRepository;

    @Override
    public Smartphone save(Smartphone smartphone) {
        return iSmartphoneRepository.save(smartphone);
    }

    @Override
    public List<Smartphone> findAll() {
        return iSmartphoneRepository.findAll();
    }

    @Override
    public Optional<Smartphone> findById(Long id) {
        return iSmartphoneRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iSmartphoneRepository.deleteById(id);
    }

}
