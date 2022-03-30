package project.models;

public class EmailDetail {
    private String email;
    private String language;
    private String pageSize;
    private String spamStatus;
    private String signature;

    public EmailDetail() {
    }

    public EmailDetail(String email, String language, String pageSize, String spamStatus, String signature) {
        this.email = email;
        this.language = language;
        this.pageSize = pageSize;
        this.spamStatus = spamStatus;
        this.signature = signature;
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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSpamStatus() {
        return spamStatus;
    }

    public void setSpamStatus(String spamStatus) {
        this.spamStatus = spamStatus;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
