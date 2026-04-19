package database.web;

import database.service.UserRegistrationService;
import database.web.model.UserRegistrationRequest;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;


@Path("/registrazione")
public class RegistrazionePage {

    private final Template registrazioneTemplate;
    private final UserRegistrationService userRegistrationService;

    public RegistrazionePage (@Location("registrazione.qute.html") Template registrazioneTemplate, UserRegistrationService userRegistrationService) {
        this.registrazioneTemplate = registrazioneTemplate;
        this.userRegistrationService = userRegistrationService;
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showRegistrazione(){
        return registrazioneTemplate.instance().data("success", false).data("errorMessage", false);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance doRegistrazione(
            @FormParam("username") String username,
            @FormParam("password") String hashedPassword
    ){
        Optional<String> optionalErrore = userRegistrationService.register(new UserRegistrationRequest(username, hashedPassword));

        TemplateInstance istanzaPagina = registrazioneTemplate.instance();

        if (optionalErrore.isPresent()) {
            return istanzaPagina
                    .data("errorMessage", true)
                    .data("success", false);
        } else {
            return istanzaPagina
                    .data("success", true)
                    .data("errorMessage", false);
        }
    }
}
