package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Sach;
import project.models.TheMuonSach;
import project.repositories.ITheMuonSachRepository;

import java.util.List;

@Service
public class TheMuonSachService implements ITheMuonSachService{

    @Autowired
    ITheMuonSachRepository iTheMuonSachRepository;

    @Override
    public void save(TheMuonSach theMuonSach) {
        iTheMuonSachRepository.save(theMuonSach);
    }

    @Override
    public TheMuonSach findById(String maMuonSach) {
        return iTheMuonSachRepository.findById(maMuonSach).orElse(null);
    }

    @Override
    public List<TheMuonSach> findAll() {
        return iTheMuonSachRepository.findAll();
    }

    @Override
    public void removeByMa(String maMuonSach) {
        iTheMuonSachRepository.deleteById(maMuonSach);
    }
}
