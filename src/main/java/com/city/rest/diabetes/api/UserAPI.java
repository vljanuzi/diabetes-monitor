/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.api;

import com.city.rest.diabetes.service.UserServices;
import com.city.rest.diabetes.utilities.Users;
import com.city.rest.diabetes.utilities.WSResponse;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Fin
 */
@Path("user")
public class UserAPI  extends WSResponse{
    @EJB
    UserServices services;
    
    public UserAPI() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(Users user) {
     
        Users response = services.doLogin(user.getUsername(), user.getPassword());
        
        if(response == null) {
            return response(jsonResponse(401, "Incorrect username or password", null));
        } else {
              return response(jsonResponse(200, "Success", response));
        }
    }
}
