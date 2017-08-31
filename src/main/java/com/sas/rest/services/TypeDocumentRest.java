/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.rest.services;



import com.sas.jpa.entities.TypeDocument;
import com.sas.jpa.sessions.RolFacade;
import com.sas.jpa.sessions.TypeDocumentFacade;
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
@Path("types_document")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeDocumentRest {
    
       
    @EJB
    private TypeDocumentFacade typeDocumentEJB;
    
    @GET
    public List<TypeDocument> findAll(){
    
    return typeDocumentEJB.findAll();   
    }
    
     @GET
     @Path("{id}")
     public TypeDocument findById(@PathParam("id") Integer id){
         
         return typeDocumentEJB.find(id);
     
     }
     
     @POST
     public void create(TypeDocument document){
         typeDocumentEJB.create(document);
     }
    
}
