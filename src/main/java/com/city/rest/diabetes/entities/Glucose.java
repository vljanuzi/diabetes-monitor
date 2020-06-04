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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "glucose")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Glucose.findAll", query = "SELECT g FROM Glucose g")
    , @NamedQuery(name = "Glucose.findAllOrderByDate", query = "SELECT g FROM Glucose g ORDER BY g.date")    
    , @NamedQuery(name = "Glucose.findById", query = "SELECT g FROM Glucose g WHERE g.id = :id")
    , @NamedQuery(name = "Glucose.findByLevel", query = "SELECT g FROM Glucose g WHERE g.level = :level")
    , @NamedQuery(name = "Glucose.findByDateInterval", query = "SELECT g FROM Glucose g WHERE g.date >= :dateStart AND g.date <= :dateEnd ORDER BY g.date ASC")
    , @NamedQuery(name = "Glucose.findByDate", query = "SELECT g FROM Glucose g WHERE g.date = :date")})


public class Glucose implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="glucose_sequence")
    @SequenceGenerator(name="glucose_sequence", sequenceName="glucose_sequence", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "level")
    private BigDecimal level;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date",columnDefinition = "DATE")
    //@Temporal(TemporalType.DATE)
    private LocalDate date;

    public Glucose() {
    }

    public Glucose(Long id) {
        this.id = id;
    }

    public Glucose(Long id, BigDecimal level, LocalDate date) {
        this.id = id;
        this.level = level;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
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
        if (!(object instanceof Glucose)) {
            return false;
        }
        Glucose other = (Glucose) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.city.rest.diabetes.entities.Glucose[ id=" + id + " ]";
    }
    
}
