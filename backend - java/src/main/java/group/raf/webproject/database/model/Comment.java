package group.raf.webproject.database.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Articleid")
    private Integer articleId;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "content", nullable = false)
    private String content;

    // Default constructor
    public Comment() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", article=" + articleId +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

