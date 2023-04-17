package BItlabJavaEE;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private String comment;
    private Timestamp postDate;
    private User userComment;
    private News newsComment;

    public Comment(Long id, String comment, Timestamp postDate, User userComment, News newsComment) {
        this.id = id;
        this.comment = comment;
        this.postDate = postDate;
        this.userComment = userComment;
        this.newsComment = newsComment;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User userComment) {
        this.userComment = userComment;
    }

    public News getNewsComment() {
        return newsComment;
    }

    public void setNewsComment(News newsComment) {
        this.newsComment = newsComment;
    }
}
