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
@Table(name = "carbohydrate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carbohydrate.findAll", query = "SELECT c FROM Carbohydrate c")
    , @NamedQuery(name = "Carbohydrate.findById", query = "SELECT c FROM Carbohydrate c WHERE c.id = :id")
    , @NamedQuery(name = "Carbohydrate.findByAmount", query = "SELECT c FROM Carbohydrate c WHERE c.amount = :amount")
    , @NamedQuery(name = "Carbohydrate.findByDateInterval", query = "SELECT c FROM Carbohydrate c WHERE c.date >= :dateStart AND c.date <= :dateEnd ORDER BY c.date ASC")
    , @NamedQuery(name = "Carbohydrate.findByDate", query = "SELECT c FROM Carbohydrate c WHERE c.date = :date")})


public class Carbohydrate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carbohydrate_sequence")
    @SequenceGenerator(name="carbohydrate_sequence", sequenceName="carbohydrate_sequence", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date",columnDefinition = "DATE")
    //@Temporal(TemporalType.DATE)
    private LocalDate date;

    public Carbohydrate() {
    }

    public Carbohydrate(Long id) {
        this.id = id;
    }

    public Carbohydrate(Long id, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        if (!(object instanceof Carbohydrate)) {
            return false;
        }
        Carbohydrate other = (Carbohydrate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.city.rest.diabetes.entities.Carbohydrate[ id=" + id + " ]";
    }
    
}
