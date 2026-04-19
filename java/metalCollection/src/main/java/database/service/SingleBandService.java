package database.service;

import database.persistence.Entity.Band;
import database.persistence.Entity.Genre;
import database.persistence.Entity.Song;
import database.persistence.repository.BandRepository;
import database.persistence.repository.GenreRepository;
import database.persistence.repository.SongRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SingleBandService {
    @Inject GenreRepository genreRepository;
    @Inject BandRepository bandRepository;
    @Inject SongRepository songRepository;

    public List<Band> findAllBands() {
        return bandRepository.listAll();
    }

    public List<Genre> findAllGenres() {
        return genreRepository.listAll();
    }

    public List<Song> findAllSongs() {
        return songRepository.listAll();
    }

    public Band findBandByName(String name) {
        return bandRepository.find("name", name).firstResult();
    }

        public List<Song> findSongsByBand(Band bandOggetto) {
            return songRepository.find("band", bandOggetto).list();
        }

    public Optional<Song> findSongsBytitle(Song song) {
            return  songRepository.find("song", song).firstResultOptional();
        }
}