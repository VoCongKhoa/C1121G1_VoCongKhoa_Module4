package project.repositories.contractDetail;

import project.models.contract.Contract;
import project.models.contractDetail.AttachService;


public interface IContractDetailViewDto {
    int getContractDetailId();
    int getQuantity();
    int getContractId();
    int getAttachServiceId();
    String getAttachServiceName();
}
