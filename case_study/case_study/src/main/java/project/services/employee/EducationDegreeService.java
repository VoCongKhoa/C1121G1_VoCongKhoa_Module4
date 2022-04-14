package project.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.employee.EducationDegree;
import project.repositories.employee.IEducationDegreeRepository;

import java.util.List;

@Service
public class EducationDegreeService implements IEducationDegreeService{

    @Autowired
    IEducationDegreeRepository iEducationDegreeRepository;

    @Override
    public List<EducationDegree> findAllActive() {
        return iEducationDegreeRepository.findAllActive();
    }
}
