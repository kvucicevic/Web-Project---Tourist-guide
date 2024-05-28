package group.raf.webproject.database.model;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Objects;

@Entity
@Table(name = "Event")
public class Event implements Serializable {

    @EmbeddedId
    private EventId id;

    public Event() {
    }

    public Event(EventId id) {
        this.id = id;
    }

    public EventId getId() {
        return id;
    }

    public void setId(EventId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

