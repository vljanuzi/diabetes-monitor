/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fin
 */
public class WSResponse {
    ObjectMapper mapper;
    JsonResponse jsonResponse;
    
    public String response(Object object) {
        if(mapper == null ) {
            mapper = new ObjectMapper();
        }
        
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(WSResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public JsonResponse jsonResponse(int status, String message,Object object) {
        if(jsonResponse == null) {
            jsonResponse = new JsonResponse(status, message,object);
        } else {
            jsonResponse.setMessage(message);
            jsonResponse.setStatus(status);
            jsonResponse.setObjects(object);
        }
        return jsonResponse;
    }
   
}
