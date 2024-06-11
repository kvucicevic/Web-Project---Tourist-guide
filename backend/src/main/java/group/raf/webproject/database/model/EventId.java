package group.raf.webproject.database.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventId implements Serializable {

    @Column(name = "Articleid")
    private Integer articleId;

    @Column(name = "Activityid")
    private Integer activityId;

    public EventId() {
    }

    public EventId(Integer articleId, Integer activityId) {
        this.articleId = articleId;
        this.activityId = activityId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(articleId, eventId.articleId) && Objects.equals(activityId, eventId.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, activityId);
    }
}
