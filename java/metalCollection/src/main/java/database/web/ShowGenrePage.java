package database.web;

import database.persistence.Entity.Genre;
import database.service.ShowGenreService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.NotFoundException;

@Path("/genre/{name}")
public class ShowGenrePage {

    private final ShowGenreService showGenreService;
    private final Template genreTemplate;

    public ShowGenrePage(
            ShowGenreService showGenreService,
            @Location("genre.detail.qute.html") Template genreTemplate
    ) {
        this.showGenreService = showGenreService;
        this.genreTemplate = genreTemplate;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getGenrePage(@PathParam("name") String name) {

        Genre genre = showGenreService.getGenreWithSongs(name);

        if (genre == null) {
            throw new NotFoundException("Il genere " + name + " non esiste nel database.");
        }

        return genreTemplate.instance()
                .data("genre", genre)
                .data("songs", genre.getSongs());
    }
}