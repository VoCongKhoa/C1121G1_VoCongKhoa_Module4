package project.services;

import project.models.TheMuonSach;

public interface ITheMuonSachService {
    void save(TheMuonSach theMuonSach);

    TheMuonSach findById(String maMuonSach);
}
