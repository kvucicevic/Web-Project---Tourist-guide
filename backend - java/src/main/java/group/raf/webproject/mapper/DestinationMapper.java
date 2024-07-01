package group.raf.webproject.mapper;

import group.raf.webproject.database.model.Destination;
import group.raf.webproject.dto.destination.DestinationRequestDTO;
import group.raf.webproject.dto.destination.DestinationResponseDTO;

public class DestinationMapper {

    public Destination destinationRequestDTOToDestination(DestinationRequestDTO destinationRequestDTO) {
        Destination destination = new Destination();
        destination.setName(destinationRequestDTO.getName());
        destination.setDescription(destinationRequestDTO.getDescription());
        return destination;
    }

    public DestinationResponseDTO destinationToDestinationResponseDTO(Destination destination) {
        DestinationResponseDTO destinationResponseDTO = new DestinationResponseDTO();
        destinationResponseDTO.setId(destination.getId());
        destinationResponseDTO.setDescription(destination.getDescription());
        destinationResponseDTO.setName(destination.getName());
        return destinationResponseDTO;
    }
}
