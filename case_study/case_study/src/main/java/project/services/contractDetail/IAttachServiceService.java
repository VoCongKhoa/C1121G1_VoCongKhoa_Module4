package project.services.contractDetail;

import project.models.contractDetail.AttachService;

import java.util.List;

public interface IAttachServiceService {
    List<AttachService> findAllActive();
}
