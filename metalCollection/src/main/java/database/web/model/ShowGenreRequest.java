package database.web.model;

public class ShowGenreRequest {
    private String name;

    public ShowGenreRequest() {}

    public ShowGenreRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}