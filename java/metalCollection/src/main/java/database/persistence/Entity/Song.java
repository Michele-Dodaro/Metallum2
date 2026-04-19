package database.persistence.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_band", nullable = false)
    private Band band;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title; // CAMBIATO: da name a title per coerenza con DB e Qute

    @Column(name = "duration_minute")
    private Integer durationMinute;

    @Column(name = "duration_second")
    private int durationSecond;

    @ManyToMany(fetch = FetchType.EAGER) // Aggiunto EAGER per evitare LazyInitializationException nel template
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "id_song"),
            inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private Set<Genre> genres = new HashSet<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    // CORRETTO: Adesso Qute troverà {song.title}
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Band getBand() { return band; }
    public void setBand(Band band) { this.band = band; }

    public Integer getDurationMinute() { return durationMinute; }
    public void setDurationMinute(Integer durationMinute) { this.durationMinute = durationMinute; }

    public int getDurationSecond() { return durationSecond; }
    public void setDurationSecond(int durationSecond) { this.durationSecond = durationSecond; }

    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) { this.genres = genres; }
}