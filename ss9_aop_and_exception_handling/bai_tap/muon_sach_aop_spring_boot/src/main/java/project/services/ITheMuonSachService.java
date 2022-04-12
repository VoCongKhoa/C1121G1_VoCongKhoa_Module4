package project.services;

import project.models.TheMuonSach;

import java.util.List;
import java.util.Set;

public interface ITheMuonSachService {
    void save(TheMuonSach theMuonSach);

    TheMuonSach findById(String maMuonSach);

    List<TheMuonSach> findAll();

    void removeByMa(String maMuonSach);
}
