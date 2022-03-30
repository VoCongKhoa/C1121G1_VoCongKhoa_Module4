package project.repositories;

import org.springframework.stereotype.Repository;
import project.models.EmailDetail;

import java.util.*;

@Repository
public class EmailDetailRepository implements IEmailDetailRepository {
    private static Map<Integer, EmailDetail> emailDetailMap = new TreeMap<>();
    private static List<String> languageList;
    private static List<Integer> pageSizeList;
    static int autoInrement = 0;

    static {
        EmailDetail emailDetail1 = new EmailDetail(autoInrement++, "abc@gmail.com", "English", 25, true, "ABC");
        EmailDetail emailDetail2 = new EmailDetail(autoInrement++, "def@gmail.com", "Vietnamese", 50, false, "DEF");
        EmailDetail emailDetail3 = new EmailDetail(autoInrement++, "ghk@gmail.com", "Japanese", 100, false, "GHK");
        EmailDetail emailDetail4 = new EmailDetail(autoInrement++, "uiv@gmail.com", "Chinese", 10, true, "UIV");
        emailDetailMap.put(emailDetail1.getId(), emailDetail1);
        emailDetailMap.put(emailDetail2.getId(), emailDetail2);
        emailDetailMap.put(emailDetail3.getId(), emailDetail3);
        emailDetailMap.put(emailDetail4.getId(), emailDetail4);
        languageList = Arrays.asList("English", "Vietnamese", "Japanese", "Chinese");
        pageSizeList = Arrays.asList(5, 10, 15, 25, 50, 100);
    }

    @Override
    public List<EmailDetail> findAll() {
        return new ArrayList<>(emailDetailMap.values());
    }

    @Override
    public EmailDetail findOne(int id) {
        return emailDetailMap.get(id);
    }

    @Override
    public void save(EmailDetail emailDetail) {
        emailDetail.setId(emailDetailMap.size()+1);
        emailDetailMap.put(emailDetail.getId(),emailDetail);
    }

    @Override
    public void update(EmailDetail emailDetail) {
        emailDetailMap.put(emailDetail.getId(),emailDetail);
    }

    @Override
    public List<String> getLanguageList() {
        return languageList;
    }

    @Override
    public List<Integer> getPageSizeList() {
        return pageSizeList;
    }

}
