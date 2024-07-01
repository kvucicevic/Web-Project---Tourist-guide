package group.raf.webproject.dto.destination;

public class DestinationResponseDTO {

    private Integer id;

    private String name;

    private String description;

    public DestinationResponseDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DestinationResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
