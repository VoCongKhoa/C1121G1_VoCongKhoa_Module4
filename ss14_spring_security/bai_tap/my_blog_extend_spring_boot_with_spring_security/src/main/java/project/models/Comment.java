package project.models;

import javax.persistence.*;

@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_date", columnDefinition = "DATE")
    private String commentDate;
    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "post_id",referencedColumnName = "post_id")
    private Post post;

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
