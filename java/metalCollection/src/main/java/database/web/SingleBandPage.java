package database.web;

import database.persistence.Entity.Band;
import database.service.SingleBandService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.NotFoundException;

@Path("/band/{name}")
public class SingleBandPage {

    private final SingleBandService singleBandService;
    private final Template singleBandTemplate;

    public SingleBandPage(
            SingleBandService singleBandService,
            @Location("single.band.qute.html") Template singleBandTemplate
    ) {
        this.singleBandService = singleBandService;
        this.singleBandTemplate = singleBandTemplate;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getSingleBand(@PathParam("name") String name) {
        Band bandOggetto = singleBandService.findBandByName(name);

        if (bandOggetto == null) {
            throw new NotFoundException("Band non trovata nel database: " + name);
        }

        return singleBandTemplate.instance()
                .data("band", bandOggetto)
                .data("songs", singleBandService.findSongsByBand(bandOggetto))
                .data("genres", singleBandService.findAllGenres());
    }
}