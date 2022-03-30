package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.EmailDetail;
import project.repositories.IEmailDetailRepository;

import java.util.List;

@Service
public class EmailDetailService implements IEmailDetailService{
    @Autowired
    IEmailDetailRepository iEmailDetailRepository;

    public List<EmailDetail> findAll(){
        return iEmailDetailRepository.findAll();
    }

    @Override
    public EmailDetail findOne(int id) {
        return iEmailDetailRepository.findOne(id);
    }

    @Override
    public void save(EmailDetail emailDetail) {
        iEmailDetailRepository.save(emailDetail);
    }

    @Override
    public void update(EmailDetail emailDetail) {
        iEmailDetailRepository.update(emailDetail);
    }

    @Override
    public List<String> getLanguageList() {
        return iEmailDetailRepository.getLanguageList();
    }

    @Override
    public List<Integer> getPageSizeList() {
        return iEmailDetailRepository.getPageSizeList();
    }
}
