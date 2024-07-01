package group.raf.webproject.service.destination;

import group.raf.webproject.database.model.Activity;
import group.raf.webproject.database.model.Destination;
import group.raf.webproject.dto.activity.ActivityRequestDTO;
import group.raf.webproject.dto.activity.ActivityResponseDTO;
import group.raf.webproject.dto.destination.DestinationRequestDTO;
import group.raf.webproject.dto.destination.DestinationResponseDTO;
import group.raf.webproject.mapper.ActivityMapper;
import group.raf.webproject.mapper.DestinationMapper;
import group.raf.webproject.repository.activity.ActivityRepository;
import group.raf.webproject.repository.destination.DestinationRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DestinationServiceImpl implements DestinationService {

    @Inject
    private DestinationRepository destinationRepository;

    @Inject
    private ActivityRepository activityRepository;

    @Inject
    private DestinationMapper destinationMapper;

    @Inject
    private ActivityMapper activityMapper;

    @Override
    public DestinationResponseDTO add(DestinationRequestDTO destinationRequestDTO) {
        Destination destination = destinationMapper.destinationRequestDTOToDestination(destinationRequestDTO);
        return destinationMapper.destinationToDestinationResponseDTO(destinationRepository.addDestination(destination));
    }

    @Override
    public List<DestinationResponseDTO> findAll() {
        List<DestinationResponseDTO> destinationResponseDTOS = new ArrayList<>();
        for(Destination destination : destinationRepository.allDestinations()){
            destinationResponseDTOS.add(destinationMapper.destinationToDestinationResponseDTO(destination));
        }
        return destinationResponseDTOS;
    }

    @Override
    public DestinationResponseDTO findById(Integer id) {
        Destination destination = destinationRepository.getDestinationById(id);
        return destinationMapper.destinationToDestinationResponseDTO(destination);
    }

    @Override
    public DestinationResponseDTO findByName(String name) {
        Destination destination = destinationRepository.getDestinationByName(name);
        return destinationMapper.destinationToDestinationResponseDTO(destination);
    }

    @Override
    public DestinationResponseDTO update(Integer id, DestinationRequestDTO destinationRequestDTO) {
        Destination destination = destinationMapper.destinationRequestDTOToDestination(destinationRequestDTO);
        destination.setId(id);
        return destinationMapper.destinationToDestinationResponseDTO(destinationRepository.updateDestination(destination));
    }

    @Override
    public DestinationResponseDTO delete(Integer id) {
        return destinationMapper.destinationToDestinationResponseDTO(destinationRepository.deleteDestination(id));
    }

    @Override
    public List<ActivityResponseDTO> allActivitiesForDestination(Integer id) {
        List<ActivityResponseDTO> activityResponseDTOS = new ArrayList<>();
        for(Activity activity : activityRepository.allCommentsForDestination(id)){
            activityResponseDTOS.add(activityMapper.activityToactivityResponseDTO(activity));
        }
        return activityResponseDTOS;

    }

    @Override
    public ActivityResponseDTO addActivity(Integer id, ActivityRequestDTO activityRequestDTO) {
        Activity activity = activityRepository.addActivityForDestination(id, activityMapper.activityRequestDTOToactivity(activityRequestDTO));
        return activityMapper.activityToactivityResponseDTO(activity);
    }
}
