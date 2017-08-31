/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.rest.services;


import com.sas.jpa.entities.Equipment;
import com.sas.jpa.sessions.EquipmentFacade;
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
@Path("equipments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EquipmentRest {
    
       
    @EJB
    private EquipmentFacade equipmentEJB;
    
    @GET
    public List<Equipment> findAll(){
    
    return equipmentEJB.findAll();   
    }
    
     @GET
     @Path("{id}")
     public Equipment findById(@PathParam("id") Integer id){
         
         return equipmentEJB.find(id);
     
     }
     
     @POST
     public void create(Equipment equipment){
         equipmentEJB.create(equipment);
     }
    
}
