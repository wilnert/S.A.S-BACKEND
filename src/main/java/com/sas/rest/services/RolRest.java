/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.rest.services;


import com.sas.jpa.entities.Equipment;
import com.sas.jpa.entities.Rol;
import com.sas.jpa.sessions.EquipmentFacade;
import com.sas.jpa.sessions.RolFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author grayf
 */
@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolRest {
    
       
    @EJB
    private RolFacade rolEJB;
    
    @GET
    public List<Rol> findAll(){
    
    return rolEJB.findAll();   
    }
    
     @GET
     @Path("{id}")
     public Rol findById(@PathParam("id") String id){
         
         return rolEJB.find(id);
     
     }
     
     @POST
     public void create(Rol rol){
         rolEJB.create(rol);
     }
    
}
