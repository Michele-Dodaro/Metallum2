package database.web;

import database.persistence.Entity.Song;
import database.persistence.repository.SongRepository;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.NotFoundException;

@Path("/canzone/{title}")
public class SingleSongPage {

    private final SongRepository songRepository;
    private final Template singleSongTemplate;

    public SingleSongPage(
            SongRepository songRepository,
            @Location("single.song.qute.html") Template singleSongTemplate
    ) {
        this.songRepository = songRepository;
        this.singleSongTemplate = singleSongTemplate;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showSong(@PathParam("title") String title) {
        Song song = songRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Canzone non trovata: " + title));

        return singleSongTemplate.instance()
                .data("song", song)
                .data("band", song.getBand())
                .data("genres", song.getGenres());
    }
}