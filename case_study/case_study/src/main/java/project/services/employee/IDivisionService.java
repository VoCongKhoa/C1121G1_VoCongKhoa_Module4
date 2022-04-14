package project.services.employee;

import project.models.employee.Division;

import java.util.List;

public interface IDivisionService {

    List<Division> findAllActive();

}
