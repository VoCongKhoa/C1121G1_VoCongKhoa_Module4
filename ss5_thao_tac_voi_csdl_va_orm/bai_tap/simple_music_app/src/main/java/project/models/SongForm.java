package project.models;

import org.springframework.web.multipart.MultipartFile;

public class SongForm {

    private Integer id;
    private String name;
    private String singer;
    private String category;
    private MultipartFile multipartFile;

    public SongForm() {
    }

    public SongForm(Integer id, String name, String singer, String category, MultipartFile multipartFile) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
        this.multipartFile = multipartFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
