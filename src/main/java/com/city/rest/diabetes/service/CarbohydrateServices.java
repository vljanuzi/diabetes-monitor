/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.service;

import com.city.rest.diabetes.entities.Carbohydrate;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CarbohydrateServices {
    
    @PersistenceContext
    EntityManager em;
    
    public Carbohydrate addCarbohydrate(Carbohydrate carb) {
        if (carb.getId() == null && carb.getDate() == null) {

            List<Carbohydrate> checkExisting = em.createNamedQuery("Carbohydrate.findByDate", Carbohydrate.class)
                    .setParameter("date", LocalDate.now())
                    .getResultList();

            if (checkExisting.isEmpty()) {
                carb.setDate(LocalDate.now());
                em.persist(carb);
            } else {
                carb = null;
            }

        } else if (carb.getDate() != null) {
            try {
                Carbohydrate old = em.createNamedQuery("Carbohydrate.findByDate", Carbohydrate.class)
                        .setParameter("date", carb.getDate())
                        .getSingleResult();

                old.setAmount(carb.getAmount());
                em.merge(old);
                carb = old;
            } catch (Exception e) {
                carb = null;
            }
        }
        return carb;
    }
    
    public List<Carbohydrate> getList() {
        return em.createNamedQuery("Carbohydrate.findAll", Carbohydrate.class).getResultList();
    }
    
    public List<Carbohydrate> getListByTime(int days) {
        
        LocalDate now = LocalDate.now();

        return em.createNamedQuery("Carbohydrate.findByDateInterval", Carbohydrate.class)
                .setParameter("dateStart", now.minusDays(days))
                .setParameter("dateEnd", now)
                .getResultList();
    }
    
    public Boolean removeCarbohydrate(Carbohydrate carb){
        List<Carbohydrate> founded = em.createNamedQuery("Carbohydrate.findByDate", Carbohydrate.class)
                .setParameter("date", carb.getDate())
                .getResultList();
        try {
//        if(!em.contains(founded)) {
//            founded = em.merge(founded);         
//        }  

            for (Carbohydrate car : founded) {
                em.remove(car);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
