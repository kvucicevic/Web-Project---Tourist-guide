package group.raf.webproject.repository.activity;

import group.raf.webproject.database.model.Activity;
import group.raf.webproject.database.model.Comment;

import java.util.List;

public interface ActivityRepository {

    Activity addActivityForDestination(Integer id, Activity activity);
    List<Activity> allCommentsForDestination(Integer destinationId);
}
