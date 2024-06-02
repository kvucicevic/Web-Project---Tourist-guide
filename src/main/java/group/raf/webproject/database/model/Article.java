package group.raf.webproject.database.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Userid", referencedColumnName = "id")
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "visitNo")
    private Integer visitNo;

    @ManyToOne
    @JoinColumn(name = "Destinationid", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_article_destination", value = ConstraintMode.CONSTRAINT))
    private Destination destination;

    // Default constructor
    public Article() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", visitNo=" + visitNo +
                ", destination=" + destination +
                '}';
    }
}

