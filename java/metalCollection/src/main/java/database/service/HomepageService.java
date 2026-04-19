package database.service;

import database.persistence.Entity.*;
import database.persistence.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HomepageService {

    @Inject
    UserRepository userRepository;

    @Inject
    BandRepository bandRepository;

    @Inject
    GenreRepository genreRepository;
    @Inject
    SongRepository songRepository;

    public User findByUsername(String username) {
        return userRepository.find("username", username).firstResult();
    }

    public List<User> findAllUsers() {
        return userRepository.listAll();
    }

    public List<Band> findAllBands() {
        return bandRepository.listAll();
    }

    public List<Genre> findAllGenres() {
        return genreRepository.listAll();
    }

    public String findBandByName(String name) {
        return name;
    }

    public Optional<Song> findRandomSong(){return songRepository.findRandomSong(); }

    @Transactional
    public void saveBand(Band band) {
        bandRepository.persist(band);
    }
}