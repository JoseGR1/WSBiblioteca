package com.edu.umg.ws;

import com.edu.umg.controller.UsuarioController;
import com.edu.umg.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("usuarios") // Ruta para acceder a las operaciones de usuario
public class UsuarioResource {
    
    @Context
    private UriInfo context;
    private UsuarioController usuarioController = new UsuarioController();

    public UsuarioResource() {};
    
    // Obtener todos los usuarios
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() {
        return usuarioController.getAllUsuarios();
    }

    // Obtener usuario por ID
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") int id) {
        Usuario usuario = usuarioController.getUsuarioById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }
    
    // Búsqueda por nombre parcial
    @GET
    @Path("/buscar/{nombreParcial}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosByNombreParcial(@PathParam("nombreParcial") String nombreParcial) {
        List<Usuario> usuarios = usuarioController.getUsuarioByNombreParcial(nombreParcial);
        if (usuarios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron usuarios con ese nombre").build();
        }
        return Response.ok(usuarios).build();
    }

    // Crear un nuevo usuario
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        usuarioController.createUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    // Actualizar un usuario existente
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        usuario.setId_usuario(id);
        usuarioController.updateUsuario(usuario);
        return Response.ok(usuario).build();
    }

}
