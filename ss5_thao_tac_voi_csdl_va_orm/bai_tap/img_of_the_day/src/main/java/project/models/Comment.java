package project.models;

import javax.persistence.*;

@Entity(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="comment_vote")
    private Integer commentVote;
    @Column(name="comment_author")
    private String commentAuthor;
    @Column(name="comment_feedback")
    private String commentFeedback;
    @Column(name="comment_date", columnDefinition = "DATE")
    private String commentDate;
    @Column(name="comment_like")
    private Integer commentLike;

    public Comment() {
    }

    public Comment(Integer id, Integer commentVote, String commentAuthor, String commentFeedback, String commentDate, Integer commentLike) {
        this.id = id;
        this.commentVote = commentVote;
        this.commentAuthor = commentAuthor;
        this.commentFeedback = commentFeedback;
        this.commentDate = commentDate;
        this.commentLike = commentLike;
    }

    public Comment(Integer commentVote, String commentAuthor, String commentFeedback, Integer commentLike) {
        this.commentVote = commentVote;
        this.commentAuthor = commentAuthor;
        this.commentFeedback = commentFeedback;
        this.commentLike = commentLike;
    }

    public Comment(Integer commentVote, String commentAuthor, String commentFeedback, String commentDate, Integer commentLike) {
        this.commentVote = commentVote;
        this.commentAuthor = commentAuthor;
        this.commentFeedback = commentFeedback;
        this.commentDate = commentDate;
        this.commentLike = commentLike;
    }

    public Comment(Integer id, String commentDate, Integer commentLike) {
        this.id = id;
        this.commentDate = commentDate;
        this.commentLike = commentLike;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentVote() {
        return commentVote;
    }

    public void setCommentVote(Integer commentVote) {
        this.commentVote = commentVote;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentFeedback() {
        return commentFeedback;
    }

    public void setCommentFeedback(String commentFeedback) {
        this.commentFeedback = commentFeedback;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(Integer commentLike) {
        this.commentLike = commentLike;
    }
}
