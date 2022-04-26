package project.services.contractDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.contractDetail.AttachService;
import project.repositories.contractDetail.IAttachServiceRepository;

import java.util.List;

@Service
public class AttachServiceService implements IAttachServiceService{

    @Autowired
    IAttachServiceRepository iAttachServiceRepository;

    @Override
    public List<AttachService> findAllActive() {
        return iAttachServiceRepository.findAllActive();
    }

    @Override
    public AttachService findAttachServiceViewById(int id) {
        return iAttachServiceRepository.findAttachServiceViewById(id);
    }
}
