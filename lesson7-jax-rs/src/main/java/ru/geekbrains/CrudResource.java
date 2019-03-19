package ru.geekbrains;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.representation.ProductRepr;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/")
@Api(value = "SimpleCrudRestService", description = "JAX-RS and Swagger Demo")
public class CrudResource {

    private static final Logger logger = LoggerFactory.getLogger(CrudResource.class);

    @Inject
    private ProductService productService;

    @GET
    @Path("/")
    @ApiOperation(value = "Check is service alive")
    @Produces(MediaType.TEXT_HTML)
    public Response checkAlive() {
        logger.info("App is alive");
        return Response.ok("App is alive").build();
    }

    @GET
    @Path("/products/count")
    @ApiOperation(value = "Get count of products")
    @Produces(MediaType.APPLICATION_JSON)
    public long getCount() {
        return productService.count();
    }

    @GET
    @Path("/products/all")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all products")
    public Collection<ProductRepr> getAllProducts() {
        logger.info("Get all products");
        return productService.getAll();
    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete product by id")
    public ProductRepr getProductById(@ApiParam(name = "id", value = "Product id to get") @PathParam("id") long id) {
        logger.info("Get product with id {}", id);

        return productService.getById(id);
    }

    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    @ApiOperation(value = "Add new product")
    public Response addProduct(@ApiParam(value = "New product to add") ProductRepr product) {
        logger.info("Add new product");

        long id = productService.addProduct(product);
        logger.info("New product id {}", id);
        return Response.accepted(String.format("New product with id %d created", id)).build();
    }

    @DELETE
    @Path("/products/{id}")
    @ApiOperation(value = "Delete product")
    public Response deleteProduct(@ApiParam(name = "id", value = "Product id to delete") @PathParam("id") long id) {
        logger.info("Delete product with id {}", id);

        productService.removeProduct(id);
        return Response.accepted().build();
    }
}
