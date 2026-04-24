package database.web;

import database.persistence.Entity.Band;
import database.service.HomepageService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/add-band")
public class AddBandPage {

    private final Template addBandTemplate;
    private final HomepageService homepageService;

    public AddBandPage(@Location("add.band.qute.html") Template addBandTemplate,
                       HomepageService homepageService) {
        this.addBandTemplate = addBandTemplate;
        this.homepageService = homepageService;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showForm() {
        return addBandTemplate.instance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveNewBand(@FormParam("bandName") String name,
                                @FormParam("provenance") String provenance) {
        Band band = new Band();
        band.setName(name);
        band.setProvenance(provenance);

        homepageService.saveBand(band);

        return Response.seeOther(URI.create("/homepage")).build();
    }
}
