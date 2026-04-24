package database.web;

import database.persistence.Entity.Band; // Importa Band
import database.persistence.Entity.Song;
import database.persistence.Entity.User;
import database.service.HomepageService;
import database.persistence.repository.SongRepository;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response; // Importa Response
import java.net.URI;
import java.util.Optional;

@Path("/homepage")
public class Homepage {

    private final Template homepageTemplate;
    private final HomepageService homepageService;
    private final SongRepository songRepository;
    private final SecurityIdentity identity;

    public Homepage(@Location("homepage.qute.html") Template homepageTemplate,
                    HomepageService homepageService,
                    SongRepository songRepository,
                    SecurityIdentity identity) {
        this.homepageTemplate = homepageTemplate;
        this.homepageService = homepageService;
        this.songRepository = songRepository;
        this.identity = identity;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showDashboard() {
        String principalName = identity.getPrincipal().getName();
        User user = homepageService.findByUsername(principalName);
        String displayName = (user != null && user.getName() != null) ? user.getName() : principalName;

        Optional<Song> randomSong = songRepository.findRandomSong();

        return homepageTemplate.instance()
                .data("username", displayName)
                .data("bands", homepageService.findAllBands())
                .data("genres", homepageService.findAllGenres())
                .data("users", homepageService.findAllUsers())
                .data("song", randomSong.orElse(null));    }
}
