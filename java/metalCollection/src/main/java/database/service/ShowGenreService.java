package database.service;

import database.persistence.Entity.Genre;
import database.persistence.repository.GenreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ShowGenreService {

    @Inject
    GenreRepository genreRepository;

    public Genre findGenreByName(String name) {
        return genreRepository.find("genre", name).firstResult();
    }

    public List<Genre> findAllGenres() {
        return genreRepository.listAll();
    }

    public Genre getGenreWithSongs(String name) {
        return genreRepository.find("genre", name).firstResult();
    }
}
