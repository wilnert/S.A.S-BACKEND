/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adsi1199561
 */
@Entity
@Table(name = "equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e")
    , @NamedQuery(name = "Equipment.findById", query = "SELECT e FROM Equipment e WHERE e.id = :id")
    , @NamedQuery(name = "Equipment.findByEquipmentcol", query = "SELECT e FROM Equipment e WHERE e.equipmentcol = :equipmentcol")
    , @NamedQuery(name = "Equipment.findByBrand", query = "SELECT e FROM Equipment e WHERE e.brand = :brand")
    , @NamedQuery(name = "Equipment.findByNumberSeries", query = "SELECT e FROM Equipment e WHERE e.numberSeries = :numberSeries")
    , @NamedQuery(name = "Equipment.findByEquipmentcol1", query = "SELECT e FROM Equipment e WHERE e.equipmentcol1 = :equipmentcol1")
    , @NamedQuery(name = "Equipment.findByModel", query = "SELECT e FROM Equipment e WHERE e.model = :model")})
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "equipmentcol")
    private String equipmentcol;
    @Size(max = 45)
    @Column(name = "brand")
    private String brand;
    @Size(max = 45)
    @Column(name = "number_series")
    private String numberSeries;
    @Size(max = 45)
    @Column(name = "equipmentcol1")
    private String equipmentcol1;
    @Size(max = 45)
    @Column(name = "model")
    private String model;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;

    public Equipment() {
    }

    public Equipment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentcol() {
        return equipmentcol;
    }

    public void setEquipmentcol(String equipmentcol) {
        this.equipmentcol = equipmentcol;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumberSeries() {
        return numberSeries;
    }

    public void setNumberSeries(String numberSeries) {
        this.numberSeries = numberSeries;
    }

    public String getEquipmentcol1() {
        return equipmentcol1;
    }

    public void setEquipmentcol1(String equipmentcol1) {
        this.equipmentcol1 = equipmentcol1;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sas.jpa.entities.Equipment[ id=" + id + " ]";
    }
    
}
