package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.HocSinh;
import project.repositories.IHocSinhRepository;
import project.repositories.ISachRepository;

import java.util.List;

@Service
public class HocSinhService implements IHocSinhService {
    @Autowired
    IHocSinhRepository iHocSinhRepository;

    @Override
    public List<HocSinh> findAll() {
        return iHocSinhRepository.findAll();
    }
}
