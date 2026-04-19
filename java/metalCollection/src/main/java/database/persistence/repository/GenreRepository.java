package database.persistence.repository;

import database.persistence.Entity.Genre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped

public class GenreRepository implements PanacheRepositoryBase<Genre, Integer> {
    public Optional<Genre> findByGenre(String genre) {
        return find("genre", genre).firstResultOptional();
    }
}
