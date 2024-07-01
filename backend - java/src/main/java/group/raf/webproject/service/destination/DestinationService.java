package group.raf.webproject.service.destination;

import group.raf.webproject.dto.activity.ActivityRequestDTO;
import group.raf.webproject.dto.activity.ActivityResponseDTO;
import group.raf.webproject.dto.destination.DestinationRequestDTO;
import group.raf.webproject.dto.destination.DestinationResponseDTO;

import java.util.List;

public interface DestinationService {

    DestinationResponseDTO add(DestinationRequestDTO destinationRequestDTO);

    List<DestinationResponseDTO> findAll();

    DestinationResponseDTO findById(Integer id);
    DestinationResponseDTO findByName(String name);

    DestinationResponseDTO update(Integer id, DestinationRequestDTO destinationRequestDTO);

    DestinationResponseDTO delete(Integer id);

    ActivityResponseDTO addActivity(Integer id, ActivityRequestDTO activityRequestDTO);

    List<ActivityResponseDTO> allActivitiesForDestination(Integer id);

}
