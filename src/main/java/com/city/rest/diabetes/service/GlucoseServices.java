/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.service;

import com.city.rest.diabetes.entities.Glucose;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateless
public class GlucoseServices {
    
    @PersistenceContext
    EntityManager em;
    
    public Glucose addGlucose(Glucose glucose) {
        if (glucose.getId() == null && glucose.getDate() == null) {

            List<Glucose> checkExisting = em.createNamedQuery("Glucose.findByDate", Glucose.class)
                    .setParameter("date", LocalDate.now())
                    .getResultList();

            if (checkExisting.isEmpty()) {
                glucose.setDate(LocalDate.now());
                em.persist(glucose);
            } else {
                glucose = null;
            }

        } else if (glucose.getDate() != null) {
            try {
                Glucose old = em.createNamedQuery("Glucose.findByDate", Glucose.class)
                        .setParameter("date", glucose.getDate())
                        .getSingleResult();

                old.setLevel(glucose.getLevel());
                em.merge(old);
                glucose = old;
            } catch (Exception e) {
                glucose = null;
            }
        }
        return glucose;
    }
    
    public List<Glucose> getList() {
        return em.createNamedQuery("Glucose.findAll", Glucose.class).getResultList();
    }
    
    public List<Glucose> getListByTime(int days) {
        
        LocalDate now = LocalDate.now();

        return em.createNamedQuery("Glucose.findByDateInterval", Glucose.class)
                .setParameter("dateStart", now.minusDays(days))
                .setParameter("dateEnd", now)
                .getResultList();
    }
    
    public Boolean removeGlucose(Glucose glucose){
        List<Glucose> founded = em.createNamedQuery("Glucose.findByDate", Glucose.class)
                .setParameter("date", glucose.getDate())
                .getResultList();
        try {
//        if(!em.contains(founded)) {
//            founded = em.merge(founded);         
//        }  

            for (Glucose med : founded) {
                em.remove(med);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
