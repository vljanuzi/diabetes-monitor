/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
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
        resources.add(com.city.rest.diabetes.api.CarbohydrateAPI.class);
        resources.add(com.city.rest.diabetes.api.GlucoseAPI.class);
        resources.add(com.city.rest.diabetes.api.MedicationAPI.class);
        resources.add(com.city.rest.diabetes.api.UserAPI.class);
        resources.add(com.city.rest.diabetes.security.JWTAdminAuthedFilter.class);
        resources.add(com.city.rest.diabetes.security.JWTAuthedFilter.class);
        resources.add(org.netbeans.rest.application.config.Security.class);
    }
    
}
