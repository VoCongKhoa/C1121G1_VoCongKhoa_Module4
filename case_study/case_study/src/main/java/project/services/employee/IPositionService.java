package project.services.employee;

import project.models.employee.Position;

import java.util.List;

public interface IPositionService {

    List<Position> findAllActive();
}
