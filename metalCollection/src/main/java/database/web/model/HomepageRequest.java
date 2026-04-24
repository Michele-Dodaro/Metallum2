package database.web.model;

public class HomepageRequest {
    private String username;
    private String name;
    private String title;

    public HomepageRequest(String username, String name, String title) {
        this.username = username;
        this.name = name;
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
