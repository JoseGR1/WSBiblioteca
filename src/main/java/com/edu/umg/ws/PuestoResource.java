package com.edu.umg.ws;

import com.edu.umg.controller.PuestoController;
import com.edu.umg.model.Puesto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("puestos") // Ruta para acceder a las operaciones de puesto
public class PuestoResource {

    private PuestoController puestoController = new PuestoController();

    // Obtener todos los puestos
    @GET
    @Produces(MediaType.APPLICATION_JSON) // Cambiar a JSON para una mejor interoperabilidad
    public List<Puesto> getPuestos() {
        return puestoController.getAllPuestos();
    }

    // Obtener puesto por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuesto(@PathParam("id") int id) {
        Puesto puesto = puestoController.getPuestoById(id);
        if (puesto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(puesto).build();
    }

    // Crear un nuevo puesto
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPuesto(Puesto puesto) {
        puestoController.createPuesto(puesto);
        return Response.status(Response.Status.CREATED).entity(puesto).build();
    }

    // Actualizar un puesto existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePuesto(@PathParam("id") int id, Puesto puesto) {
        puesto.setId_puesto(id);
        puestoController.updatePuesto(puesto);
        return Response.ok(puesto).build();
    }
}
