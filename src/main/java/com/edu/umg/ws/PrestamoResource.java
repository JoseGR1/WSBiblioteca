package com.edu.umg.ws;

import com.edu.umg.controller.PrestamoController;
import com.edu.umg.model.Prestamo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("prestamos")
public class PrestamoResource {

    private PrestamoController prestamoController = new PrestamoController();

    // Obtener todos los préstamos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prestamo> getPrestamos() {
        return prestamoController.getAllPrestamos();
    }

    // Obtener préstamo por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrestamo(@PathParam("id") int id) {
        Prestamo prestamo = prestamoController.getPrestamoById(id);
        if (prestamo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(prestamo).build();
    }

    // Crear un nuevo préstamo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPrestamo(Prestamo prestamo) {
        prestamoController.createPrestamo(prestamo);
        return Response.status(Response.Status.CREATED).entity(prestamo).build();
    }

    // Actualizar un préstamo existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrestamo(@PathParam("id") int id, Prestamo prestamo) {
        prestamo.setId_prestamo(id);
        prestamoController.updatePrestamo(prestamo);
        return Response.ok(prestamo).build();
    }

}
