package group.raf.webproject.dto.destination;

public class DestinationResponseDTO {

    private String name;

    private String description;

    public DestinationResponseDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DestinationResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
