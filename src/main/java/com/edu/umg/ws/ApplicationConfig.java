/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.ws;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jose5
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.edu.umg.ws.AutorResource.class);
        resources.add(com.edu.umg.ws.LibroResource.class);
        resources.add(com.edu.umg.ws.PersonalResource.class);
        resources.add(com.edu.umg.ws.PrestamoResource.class);
        resources.add(com.edu.umg.ws.PuestoResource.class);
        resources.add(com.edu.umg.ws.TipoResource.class);
        resources.add(com.edu.umg.ws.UsuarioResource.class);
    }
    
}
