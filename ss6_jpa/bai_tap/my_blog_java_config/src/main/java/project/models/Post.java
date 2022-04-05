package project.models;

import javax.persistence.*;

@Entity(name = "post")
public class Post {

    //id, title,intro, content, thumbnail, dateModified, authorId, commentId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Vi sao lay Long???
    @Column(name = "title")
    private String title;
    @Column(name = "intro")
    private String intro;
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;
    @Column(name = "thumbnail_source")
    private String thumbnailSource;
    @Column(name = "date_modified", columnDefinition = "DATE")
    private String dateModified;
    @Column(name = "author")
    private String author;

    public Post() {
    }

    public Post(Long id, String title, String intro, String content, String thumbnailSource, String dateModified, String author) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.content = content;
        this.thumbnailSource = thumbnailSource;
        this.dateModified = dateModified;
        this.author = author;
    }

    public Post(String title, String intro, String content, String thumbnailSource, String dateModified, String author) {
        this.title = title;
        this.intro = intro;
        this.content = content;
        this.thumbnailSource = thumbnailSource;
        this.dateModified = dateModified;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnailSource() {
        return thumbnailSource;
    }

    public void setThumbnailSource(String thumbnailSource) {
        this.thumbnailSource = thumbnailSource;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
