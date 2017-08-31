/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.rest.services;


import com.sas.jpa.entities.User;
import com.sas.jpa.sessions.UserFacade;
import com.sas.rest.auth.DigestUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sas.jpa.entities.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author grayf
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

    @EJB
    private UserFacade userEJB;



    /**
     * this method allows search all users
     *
     * @return list of users
     */
    //@RolesAllowed({"ADMIN"})
    @GET
    public List<User> findUsers() {
   
        return userEJB.findAll();
    }

 

    /**
     * this method allows search a user by id
     *
     * @param id
     * @return user
     */
    @GET
    @Path("{id}")
    @RolesAllowed({"ADMIN"})
    public User findById(@PathParam("id") Integer id) {
        
        System.out.println("id :v " + id);
        return userEJB.find(id);

    }
    
    
    @GET
    @Path("assistance")
    @RolesAllowed({"ADMIN"})
    public List<User> findAssistance() {

        return userEJB.findAssistance();
    }

    /**
     * This method receives the user data from the frontend and creates the user
     *
     * @param id     
     * @param user
     * @return
     */
    @POST
    @Path("{id}")
    public Response create(@PathParam("id") String id,User user) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        if (validateUserByIdentificationNumber(user.getIdentification())) {

            if (validateUserByEmail(user.getEmail())) {

                List<Rol> rolList = new ArrayList<>();
                rolList.add(new Rol(id));
                user.setPassword(DigestUtil.cifrarPassword(user.getPassword()));
                user.setRolList(rolList);
                userEJB.create(user);
                return Response.status(Response.Status.OK)
                        .entity(gson.toJson("El usuario fue registrado satisfactoriamente."))
                        .build();

            } else {

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(gson.toJson("El email ya se encuentra registrado!."))
                        .build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("El numero de documento ya se encuentra registrado!."))
                    .build();
        }

    }

    private boolean validateUserByEmail(String email) {

        return userEJB.findByEmail(email) == null;
    }

    private boolean validateUserByIdentificationNumber(String identificationNumber) {

        return userEJB.findByIdentificationNumber(identificationNumber) == null;
    }

    /**
     * This method allows to modify the personal user information
     *
     * @param newUser
     */
    @PUT
    public void edit(User newUser) {
        User oldUser = userEJB.find(newUser.getId());
        if (!newUser.getPassword().equals(oldUser.getPassword())) {
            newUser.setPassword(DigestUtil.cifrarPassword(newUser.getPassword()));
        }
        userEJB.edit(newUser);
    }

    /**
     * This method allows to delete a user
     *
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        userEJB.remove(userEJB.find(id));
    }
//
//    @GET
//    @Path("countUsers")
//    @Produces(MediaType.TEXT_PLAIN)
//    public int countUsers(@QueryParam("name") String name,
//            @QueryParam("idDepartment") Integer idDepartment,
//            @QueryParam("lastName") String lastName,
//            @QueryParam("idCity") Integer idCity,
//            @QueryParam("idDocumentType") String idDocumentType) {
//        return userEJB.countUsers(name, lastName, idCity, idDepartment, idDocumentType);
//    }

}
