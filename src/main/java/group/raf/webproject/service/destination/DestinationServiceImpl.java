package group.raf.webproject.service.destination;

import group.raf.webproject.database.model.Destination;
import group.raf.webproject.dto.destination.DestinationRequestDTO;
import group.raf.webproject.dto.destination.DestinationResponseDTO;
import group.raf.webproject.mapper.DestinationMapper;
import group.raf.webproject.repository.destination.DestinationRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DestinationServiceImpl implements DestinationService{

    @Inject
    private DestinationRepository destinationRepository;

    @Inject
    private DestinationMapper destinationMapper;

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
    public DestinationResponseDTO update(Integer id, DestinationRequestDTO destinationRequestDTO) {
        Destination destination = destinationRepository.getDestinationById(id);
        return destinationMapper.destinationToDestinationResponseDTO(destinationRepository.updateDestination(destination));
    }

    @Override
    public DestinationResponseDTO delete(Integer id) {
        return destinationMapper.destinationToDestinationResponseDTO(destinationRepository.deleteDestination(id));
    }
}
