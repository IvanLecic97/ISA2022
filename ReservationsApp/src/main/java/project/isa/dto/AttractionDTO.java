package project.isa.dto;

import project.isa.model.entities.Attraction;

public class AttractionDTO {
    private Attraction attraction;
    private String type;

    public AttractionDTO(Attraction attraction, String type) {
        this.attraction = attraction;
        this.type = type;
    }

    public AttractionDTO() {

    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
