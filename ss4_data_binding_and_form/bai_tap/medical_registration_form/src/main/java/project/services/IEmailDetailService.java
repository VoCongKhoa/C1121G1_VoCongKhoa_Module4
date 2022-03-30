package project.services;

import project.models.EmailDetail;

import java.util.List;

public interface IEmailDetailService {
    List<EmailDetail> findAll();

    EmailDetail findOne(int id);

    void save(EmailDetail emailDetail);

    void update(EmailDetail emailDetail);

    List<String> getLanguageList();

    List<Integer> getPageSizeList();
}
