package ru.geekbrains.rest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * Чтобы работали ограничения доступа через аннотации класс должен бвть EJB бином
 */
@Path("/")
@Stateless
public class RestController {

    @GET
    @Path("/")
    @PermitAll
    public String publicResource() {
        return "Public";
    }

    @GET
    @Path("/secure")
    @RolesAllowed("guest")
    public String secureResource() {
        return "Guest";
    }

    @GET
    @Path("/secure1")
    @PermitAll
    public String secureResource1(@Context HttpServletRequest req) {
        if (req.isUserInRole("guest")) {
            return "Guest";
        }
        throw new NotAuthorizedException("User not authorized for usage");
    }

    @GET
    @Path("/admin")
    @RolesAllowed("admin")
    public String adminResource() {
        return "Admin";
    }
}
