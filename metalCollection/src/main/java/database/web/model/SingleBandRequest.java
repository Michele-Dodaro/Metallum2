package database.web.model;

public class SingleBandRequest {
    private String name;
    private String title;
    private String provenance;
    private String genre;
    private Integer durationSecond;
    private Integer durationMinute;

    public SingleBandRequest() {}

    public SingleBandRequest(String name, String title, String provenance, String genre,
                             Integer durationMinute, Integer durationSecond) {
        this.name = name;
        this.title = title;
        this.provenance = provenance;
        this.genre = genre;
        this.durationMinute = durationMinute;
        this.durationSecond = durationSecond;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getProvenance() { return provenance; }
    public void setProvenance(String provenance) { this.provenance = provenance; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Integer getDurationSecond() { return durationSecond; }
    public void setDurationSecond(Integer durationSecond) { this.durationSecond = durationSecond; }
    public Integer getDurationMinute() { return durationMinute; }
    public void setDurationMinute(Integer durationMinute) { this.durationMinute = durationMinute; }
}