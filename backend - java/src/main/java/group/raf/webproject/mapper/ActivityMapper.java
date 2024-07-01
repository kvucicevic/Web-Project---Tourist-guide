package group.raf.webproject.mapper;

import group.raf.webproject.database.model.Activity;
import group.raf.webproject.dto.activity.ActivityRequestDTO;
import group.raf.webproject.dto.activity.ActivityResponseDTO;

public class ActivityMapper {

    public Activity activityRequestDTOToactivity(ActivityRequestDTO activityRequestDTO) {
        Activity activity = new Activity();
        activity.setId(activityRequestDTO.getId());
        activity.setName(activityRequestDTO.getName());
        activity.setDestinationId(activityRequestDTO.getDestinationId());
        return activity;
    }

    public ActivityResponseDTO activityToactivityResponseDTO(Activity activity) {
        ActivityResponseDTO activityResponseDTO = new ActivityResponseDTO();
        activityResponseDTO.setId(activity.getId());
        activityResponseDTO.setName(activity.getName());
        activityResponseDTO.setDestinationId(activity.getDestinationId());
        return activityResponseDTO;
    }
}
