package ru.geekbrains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/")
@Api(value = "SimpleCrudRestService", description = "JAX-RS and Swagger Demo")
public class CrudResource {

    private static final Logger logger = LoggerFactory.getLogger(CrudResource.class);

//    @GET
//    @Path("/")
//    public String checkAlive() {
//        logger.info("App is alive");
//        return "App is alive";
//    }

    @GET
    @Path("/products/all")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all products")
    public List<Product> getAll() {
        logger.info("Get all");
        return Collections.emptyList();
    }

    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add new product")
    public Response addProduct(@ApiParam(name = "New product to add") Product product) {
        logger.info("Add new product");
        return Response.accepted().build();
    }

    @DELETE
    @Path("/products/{id}")
    @ApiOperation(value = "Delete product")
    public Response addProduct(@PathParam("id") @ApiParam(name = "Product id") long id) {
        logger.info("Delete product with id {}", id);
        return Response.accepted().build();
    }
}
