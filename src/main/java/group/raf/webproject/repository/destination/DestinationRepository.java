package group.raf.webproject.repository.destination;

import group.raf.webproject.database.model.Destination;

import java.util.List;

public interface DestinationRepository {

    Destination addDestination(Destination destination);
    List<Destination> allDestinations();
    Destination getDestinationById(Integer id);
    Destination getDestinationByName(String name);
    Destination updateDestination(Destination destination);
    Destination deleteDestination(Integer id);

}
