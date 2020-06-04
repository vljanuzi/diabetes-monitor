/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.api;

import com.city.rest.diabetes.entities.Medication;
import com.city.rest.diabetes.security.JWTAdminAuthed;
import com.city.rest.diabetes.security.JWTAuthed;
import com.city.rest.diabetes.service.MedicationServices;
import com.city.rest.diabetes.utilities.WSResponse;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Path("medication")
public class MedicationAPI extends WSResponse {
    
    @EJB
    MedicationServices services;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JWTAuthed
    public List<Medication> getAll() {
        return services.getList();
    }
    
    @Path("/getTime")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JWTAuthed
    public List<Medication> getByTime(@QueryParam("days") int days) {
        return services.getListByTime(days);
    }
    
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTAdminAuthed
    public Response addMedication(Medication data) {
        Medication result = services.addMedication(data);
        if(result != null) {
            return Response.ok().entity(result).build();
        } else {
            return Response.status(204).build();
        }
    }
    
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTAdminAuthed
    public Response updateMedication(Medication data) {
        Medication result = services.addMedication(data);
        if(result != null) {
            return Response.ok().entity(result).build();
        } else {
            return Response.status(304).build();
        }
    }
    
    
    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTAdminAuthed
    public Response deleteMedication(Medication data){ 
        Boolean result =  services.removeMedication(data);
        
        if(result != null) {
            return Response.ok().entity(jsonResponse(200, "success", null)).build();
        } else {
            return Response.status(304).entity(jsonResponse(304, "success", null)).build();
        }  
    }  
}
