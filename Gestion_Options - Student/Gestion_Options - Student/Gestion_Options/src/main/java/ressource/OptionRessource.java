package ressource;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OptionRessource {


    private static OptionBusiness business = new OptionBusiness();


    @POST
    public Response addOption(Option option) {
        if (option != null && business.addOption(option)) {
            return Response.ok(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    public Response getAll(@QueryParam("domaine") String domaine) {
        List<Option> liste;
        if (domaine == null || domaine.isEmpty()) {
            liste = business.getListOption();
        } else {
            liste = business.getOptionsByDomaine(domaine);
        }
        return Response.ok(liste).build();
    }


    @GET
    @Path("/{code}")
    public Response getByCode(@PathParam("code") int code) {
        Option option = business.getOptionByCode(code);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(option).build();
    }


    @PUT
    @Path("/{code}")
    public Response updateOption(@PathParam("code") int code, Option option) {
        if (option != null && business.updateOption(code, option)) {
            return Response.ok(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @DELETE
    @Path("/{code}")
    public Response deleteOption(@PathParam("code") int code) {
        if (business.deleteOption(code)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}