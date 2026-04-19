package database.persistence.repository;

import database.persistence.Entity.Song;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class SongRepository implements PanacheRepositoryBase<Song, Integer> {

    public Optional<Song> findByTitle(String title) {
        return find("title", title).firstResultOptional();
    }

    public Optional<Song> findRandomSong() {
        return find("ORDER BY RANDOM()").firstResultOptional();
    }
}