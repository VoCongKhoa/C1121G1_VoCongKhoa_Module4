package project.repositories;

import project.models.EmailDetail;

import java.util.List;

public interface IEmailDetailRepository {
    List<EmailDetail> findAll();

    EmailDetail findOne(int id);

    void save(EmailDetail emailDetail);

    void update(EmailDetail emailDetail);

    List<String> getLanguageList();

    List<Integer> getPageSizeList();
}
