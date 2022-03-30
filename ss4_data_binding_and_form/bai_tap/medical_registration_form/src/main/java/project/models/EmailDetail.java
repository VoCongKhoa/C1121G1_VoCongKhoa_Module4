package project.models;

import java.util.Objects;

public class EmailDetail {
    private int id;
    private String email;
    private String language;
    private int pageSize;
    private boolean spamStatus;
    private String signature;

    public EmailDetail() {
    }

    public EmailDetail(int id, String email, String language, int pageSize, boolean spamStatus, String signature) {
        this.id = id;
        this.email = email;
        this.language = language;
        this.pageSize = pageSize;
        this.spamStatus = spamStatus;
        this.signature = signature;
    }

    public EmailDetail(String email, String language, int pageSize, boolean spamStatus, String signature) {
        this.email = email;
        this.language = language;
        this.pageSize = pageSize;
        this.spamStatus = spamStatus;
        this.signature = signature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getSpamStatus() {
        return spamStatus;
    }

    public void setSpamStatus(boolean spamStatus) {
        this.spamStatus = spamStatus;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EmailDetail that = (EmailDetail) o;
//        return id == that.id && pageSize == that.pageSize && spamStatus == that.spamStatus && Objects.equals(email, that.email) && Objects.equals(language, that.language) && Objects.equals(signature, that.signature);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, language, pageSize, spamStatus, signature);
//    }

    @Override
    public String toString() {
        return "EmailDetail{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", pageSize=" + pageSize +
                ", spamStatus=" + spamStatus +
                ", signature='" + signature + '\'' +
                '}';
    }
}
