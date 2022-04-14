package project.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.employee.Division;
import project.repositories.employee.IDivisionRepository;

import java.util.List;

@Service
public class DivisionService implements IDivisionService{

    @Autowired
    IDivisionRepository iDivisionRepository;

    @Override
    public List<Division> findAllActive() {
        return iDivisionRepository.findAllActive();
    }
}
