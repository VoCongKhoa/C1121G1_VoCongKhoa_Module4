package project.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "post")
public class Post {

    //id, title,intro, content, thumbnail, dateModified, authorId, commentId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId; //Vi sao lay Long???
    @Column(name = "post_title")
    private String postTitle;
    @Column(name = "post_intro")
    private String postIntro;
    @Column(name = "post_content", columnDefinition = "LONGTEXT")
    private String postContent;
    @Column(name = "post_thumbnail_source")
    private String postThumbnailSource;
    @Column(name = "post_date_modified", columnDefinition = "DATE")
    private String postDateModified;

    @ManyToOne
    @JoinColumn(name= "author_id", referencedColumnName = "author_id")
    private Author author;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post")
    private List<PostCategory> postCategoryList;


    public Post() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostIntro() {
        return postIntro;
    }

    public void setPostIntro(String postIntro) {
        this.postIntro = postIntro;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostThumbnailSource() {
        return postThumbnailSource;
    }

    public void setPostThumbnailSource(String postThumbnailSource) {
        this.postThumbnailSource = postThumbnailSource;
    }

    public String getPostDateModified() {
        return postDateModified;
    }

    public void setPostDateModified(String postDateModified) {
        this.postDateModified = postDateModified;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<PostCategory> getPostCategoryList() {
        return postCategoryList;
    }

    public void setPostCategoryList(List<PostCategory> postCategoryList) {
        this.postCategoryList = postCategoryList;
    }
}
