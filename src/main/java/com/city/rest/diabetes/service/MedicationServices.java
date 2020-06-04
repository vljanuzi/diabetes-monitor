/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.service;

import com.city.rest.diabetes.entities.Medication;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateless
public class MedicationServices {

    @PersistenceContext
    EntityManager em;

    public Medication addMedication(Medication medication) {
        if (medication.getId() == null && medication.getDate() == null) {

            List<Medication> checkExisting = em.createNamedQuery("Medication.findByDate", Medication.class)
                    .setParameter("date", LocalDate.now())
                    .getResultList();

            if (checkExisting.isEmpty()) {
                medication.setDate(LocalDate.now());
                em.persist(medication);
            } else {
                medication = null;
            }

        } else if (medication.getDate() != null) {
            try {
                Medication old = em.createNamedQuery("Medication.findByDate", Medication.class)
                        .setParameter("date", medication.getDate())
                        .getSingleResult();

                old.setDose(medication.getDose());
                em.merge(old);
                medication = old;
            } catch (Exception e) {
                medication = null;
            }
        }
        return medication;
    }

    public List<Medication> getList() {
        return em.createNamedQuery("Medication.findAll", Medication.class).getResultList();
    }

    public List<Medication> getListByTime(int days) {

        LocalDate now = LocalDate.now();

        return em.createNamedQuery("Medication.findByDateInterval", Medication.class)
                .setParameter("dateStart", now.minusDays(days))
                .setParameter("dateEnd", now)
                .getResultList();
    }

    public Boolean removeMedication(Medication medication) {
        List<Medication> founded = em.createNamedQuery("Medication.findByDate", Medication.class)
                .setParameter("date", medication.getDate())
                .getResultList();
        try {
//        if(!em.contains(founded)) {
//            founded = em.merge(founded);         
//        }  

            for (Medication med : founded) {
                em.remove(med);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
