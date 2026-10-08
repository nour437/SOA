package ressource;

import entities.Etudiant;
import entities.EtudiantsXml;
import entities.Option;
import metiers.EtudiantBusiness;
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
import java.util.ArrayList;
import java.util.List;

@Path("/etudiants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtudiantRessource {


    private static EtudiantBusiness business = new EtudiantBusiness();
    private static OptionBusiness optionBusiness = new OptionBusiness();


    @POST
    public Response addEtudiant(Etudiant etudiant) {
        if (etudiant != null && etudiant.getOption() != null
                && business.addEtudiant(etudiant)) {
            return Response.ok(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    public Response getAll() {
        return Response.ok(business.getAllEtudiants()).build();
    }


    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getByOption(@QueryParam("codeOption") int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Etudiant> sansOption = new ArrayList<>();
        for (Etudiant e : business.getEtudiantsByOption(option)) {
            sansOption.add(new Etudiant(e.getIdentifiant(), e.getNom(),
                    e.getPrenom(), null, e.getAnneeEtude(), e.getEmail()));
        }
        return Response.ok(new EtudiantsXml(sansOption)).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        Etudiant e = business.getEtudiantByIdentifiant(id);
        if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(e).build();
    }


    @PUT
    @Path("/{id}")
    public Response updateEtudiant(@PathParam("id") String id, Etudiant etudiant) {
        if (etudiant != null && business.updateEtudiant(id, etudiant)) {
            return Response.ok(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteEtudiant(@PathParam("id") String id) {
        if (business.deleteEtudiant(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}