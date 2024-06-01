package group.raf.webproject.dto.article;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ArticleResponseDTO {

    private String title;

    private Timestamp date;

    private String text;

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
}
