package com.edu.umg.ws;

import com.edu.umg.controller.TipoController;
import com.edu.umg.model.Tipo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tipos") // Ruta para acceder a las operaciones de tipo
public class TipoResource {

    private TipoController tipoController = new TipoController();

    // Obtener todos los tipos
    @GET
    @Produces(MediaType.APPLICATION_JSON) // Cambiar a JSON para una mejor interoperabilidad
    public List<Tipo> getTipos() {
        return tipoController.getAllTipos();
    }

    // Obtener tipo por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTipo(@PathParam("id") int id) {
        Tipo tipo = tipoController.getTipoById(id);
        if (tipo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(tipo).build();
    }
    

    // Crear un nuevo tipo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTipo(Tipo tipo) {
        tipoController.createTipo(tipo);
        return Response.status(Response.Status.CREATED).entity(tipo).build();
    }

    // Actualizar un tipo existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTipo(@PathParam("id") int id, Tipo tipo) {
        tipo.setId_tipo(id);
        tipoController.updateTipo(tipo);
        return Response.ok(tipo).build();
    }

}
