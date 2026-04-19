package database.web;

import database.service.UserLoginService;
import database.web.model.UserLoginRequest;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Optional;

@Path("/login")
public class LoginPage {

    private final Template loginTemplate;
    private final UserLoginService userLoginService;

    public LoginPage(@Location("login.qute.html") Template loginTemplate, UserLoginService userLoginService) {
        this.loginTemplate = loginTemplate;
        this.userLoginService = userLoginService;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showLogin(@QueryParam("error") boolean errore) {
        return loginTemplate.instance()
                .data("errorMessage", errore);
    }

    @GET
    @Path("/logout")
    @PermitAll
    public Response logout() {
        NewCookie removeCookie = new NewCookie.Builder("quarkus-credential")
                .path("/")
                .maxAge(0)
                .value("")
                .build();

        return Response.seeOther(URI.create("/login"))
                .cookie(removeCookie)
                .build();
    }
}