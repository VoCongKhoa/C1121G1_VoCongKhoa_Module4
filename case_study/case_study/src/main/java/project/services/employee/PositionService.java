package project.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.employee.Position;
import project.repositories.employee.IPositionRepository;

import java.util.List;

@Service
public class PositionService implements IPositionService{

    @Autowired
    IPositionRepository iPositionRepository;

    @Override
    public List<Position> findAllActive() {
        return iPositionRepository.findAllActive();
    }
}
