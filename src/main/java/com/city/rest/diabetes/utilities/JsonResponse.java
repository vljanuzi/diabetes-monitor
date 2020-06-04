/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.utilities;

/**
 *
 * @author Fin
 */
public class JsonResponse {
    
    private Integer status;
    private String message;
    private Object objects;
    
    public JsonResponse() {
        
    }

    public JsonResponse(Integer status, String message, Object objects) {
        this.status = status;
        this.message = message;
        this.objects = objects;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObjects() {
        return objects;
    }

    public void setObjects(Object objects) {
        this.objects = objects;
    }
    
   
}
