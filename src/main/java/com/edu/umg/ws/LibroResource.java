package com.edu.umg.ws;

import com.edu.umg.controller.LibroController;
import com.edu.umg.model.Libro;
import java.sql.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;

@Path("libros")
public class LibroResource {

    private LibroController libroController = new LibroController();

    // Obtener todos los libros
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> getAllLibros() {
        return libroController.getAllLibros();
    }

    // Buscar libros por título parcial
    @GET
    @Path("buscarPorTitulo/{tituloParcial}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> getLibrosByTituloParcial(@PathParam("tituloParcial") String tituloParcial) {
        return libroController.getLibrosByTituloParcial(tituloParcial);
    }

    // Obtener libro por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibro(@PathParam("id") int id) {
        Libro libro = libroController.getLibroById(id);
        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(libro).build();
    }

    // Crear un nuevo libro
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLibro(Libro libro) {
        libroController.createLibro(libro);
        return Response.status(Response.Status.CREATED).entity(libro).build();
    }

    // Actualizar un libro existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLibro(@PathParam("id") int id, Libro libro) {
        libro.setId_libro(id);
        libroController.updateLibro(libro);
        return Response.ok(libro).build();
    }
    
}
