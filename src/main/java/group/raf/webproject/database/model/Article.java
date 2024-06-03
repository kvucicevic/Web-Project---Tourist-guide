package group.raf.webproject.database.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Userid")
    private Integer userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "visitNo")
    private Integer visitNo;

    @Column(name = "Destinationid")
    private Integer destinationId;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // Default constructor
    public Article() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(Integer visitNo) {
        this.visitNo = visitNo;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + userId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", visitNo=" + visitNo +
                ", destination=" + destinationId +
                '}';
    }
}

