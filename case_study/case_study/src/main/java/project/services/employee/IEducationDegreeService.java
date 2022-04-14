package project.services.employee;


import project.models.employee.EducationDegree;

import java.util.List;

public interface IEducationDegreeService {

    List<EducationDegree> findAllActive();

}
