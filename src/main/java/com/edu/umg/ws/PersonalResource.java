package com.edu.umg.ws;

import com.edu.umg.controller.PersonalController;
import com.edu.umg.model.Personal;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("personal") // Ruta para acceder a las operaciones de personal
public class PersonalResource {

    private PersonalController personalController = new PersonalController();

    // Obtener todos los registros de Personal
    @GET
    @Produces(MediaType.APPLICATION_JSON) // Cambiar a JSON para una mejor interoperabilidad
    public List<Personal> getAllPersonal() {
        return personalController.getAllPersonal();
    }

 // Búsqueda por nombre parcial
    @GET
    @Path("/buscar/{nombreParcial}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonalByNombreParcial(@PathParam("nombreParcial") String nombreParcial) {
        List<Personal> personalList = personalController.getPersonalByNombreParcial(nombreParcial);
        if (personalList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron registros de personal con ese nombre").build();
        }
        return Response.ok(personalList).build();
    }

    // Obtener un registro de Personal por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonal(@PathParam("id") int id) {
        Personal personal = personalController.getPersonalById(id);
        if (personal == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(personal).build();
    }

    // Crear un nuevo registro de Personal
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPersonal(Personal personal) {
        personalController.createPersonal(personal);
        return Response.status(Response.Status.CREATED).entity(personal).build();
    }

    // Actualizar un registro de Personal existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonal(@PathParam("id") int id, Personal personal) {
        personal.setId_personal(id); // Asegúrate de que la entidad Personal tenga un método setId_personal
        personalController.updatePersonal(personal);
        return Response.ok(personal).build();
    }
}
