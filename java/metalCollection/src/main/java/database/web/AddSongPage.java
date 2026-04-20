package database.web;

import database.persistence.Entity.Band;
import database.persistence.Entity.Song;
import database.persistence.Entity.Genre;
import database.persistence.repository.BandRepository;
import database.persistence.repository.SongRepository;
import database.persistence.repository.GenreRepository;
import database.service.HomepageService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Path("/add-song")
public class AddSongPage {

    @Inject SongRepository songRepository;
    @Inject BandRepository bandRepository;
    @Inject HomepageService homepageService;
    @Inject GenreRepository genreRepository;

    private final Template addSongTemplate;

    public AddSongPage(@Location("add.song.qute.html") Template addSongTemplate) {
        this.addSongTemplate = addSongTemplate;
    }

    @GET
    @Path("/{bandName}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showForm(@PathParam("bandName") String bandName) {
        return addSongTemplate.instance()
                .data("bandName", bandName)
                .data("genres", homepageService.findAllGenres());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveSong(@FormParam("title") String title,
                             @FormParam("minutes") Integer minutes,
                             @FormParam("seconds") int seconds,
                             @FormParam("bandName") String bandName,
                             @FormParam("genreId") String genreName) {

        Band band = bandRepository.find("name", bandName).firstResult();
        if (band == null) {
            throw new NotFoundException("Band non trovata: " + bandName);
        }
        Genre selectedGenre = genreRepository.find("genre", genreName).firstResult();

        Song song = new Song();
        song.setTitle(title);
        song.setDurationMinute(minutes);
        song.setDurationSecond(seconds);
        song.setBand(band);

        if (selectedGenre != null) {
            song.getGenres().add(selectedGenre);
        }

        songRepository.persist(song);

        String encodedName = URLEncoder.encode(bandName, StandardCharsets.UTF_8).replace("+", "%20");
        return Response.seeOther(URI.create("/band/" + encodedName)).build();
    }
}
