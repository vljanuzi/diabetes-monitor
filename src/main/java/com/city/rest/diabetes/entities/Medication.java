/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.city.rest.diabetes.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "medication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medication.findAll", query = "SELECT m FROM Medication m")
    , @NamedQuery(name = "Medication.findById", query = "SELECT m FROM Medication m WHERE m.id = :id")
    , @NamedQuery(name = "Medication.findByDose", query = "SELECT m FROM Medication m WHERE m.dose = :dose")
    , @NamedQuery(name = "Medication.findByDate", query = "SELECT m FROM Medication m WHERE m.date = :date ORDER BY m.date")
    , @NamedQuery(name = "Medication.findByDateInterval", query = "SELECT m FROM Medication m WHERE m.date >= :dateStart AND m.date <= :dateEnd ORDER BY m.date ASC")})


public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medication_sequence")
    @SequenceGenerator(name="medication_sequence", sequenceName="medication_sequence", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "dose")
    private BigDecimal dose;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date",columnDefinition = "DATE")
    //@Temporal(TemporalType.DATE)
    private LocalDate date;

    public Medication() {
    }

    public Medication(Long id) {
        this.id = id;
    }

    public Medication(Long id, BigDecimal dose, LocalDate date) {
        this.id = id;
        this.dose = dose;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDose() {
        return dose;
    }

    public void setDose(BigDecimal dose) {
        this.dose = dose;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.city.rest.diabetes.entities.Medication[ id=" + id + " ]";
    }
    
}
