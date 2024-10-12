package com.edu.umg.ws;

import com.edu.umg.controller.AutorController;
import com.edu.umg.model.Autor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("autores") // Ruta base para Autor
public class AutorResource {

    @Context
    private UriInfo context;

    private AutorController autorController = new AutorController();

    // Obtener todos los autores
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> getAutores() {
        return autorController.getAllAutores();
    }

    // Obtener un autor por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutor(@PathParam("id") int id) {
        Autor autor = autorController.getAutorById(id);
        if (autor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(autor).build();
    }
    
    // Búsqueda por nombre parcial
    @GET
    @Path("/buscar/{nombreParcial}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutoresByNombreParcial(@PathParam("nombreParcial") String nombreParcial) {
        List<Autor> autores = autorController.getAutorByNombreParcial(nombreParcial);
        if (autores.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron autores con ese nombre").build();
        }
        return Response.ok(autores).build();
    }

    // Crear un nuevo autor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAutor(Autor autor) {
        autorController.createAutor(autor);
        return Response.status(Response.Status.CREATED).entity(autor).build();
    }

    // Actualizar un autor existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAutor(@PathParam("id") int id, Autor autor) {
        autor.setId_autor(id);
        autorController.updateAutor(autor);
        return Response.ok(autor).build();
    }

}
