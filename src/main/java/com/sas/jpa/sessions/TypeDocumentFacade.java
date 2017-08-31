/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.jpa.sessions;

import com.sas.jpa.entities.TypeDocument;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author darkreaven
 */
@Stateless
public class TypeDocumentFacade extends AbstractFacade<TypeDocument> {

    @PersistenceContext(unitName = "com.sas_BackendSAS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeDocumentFacade() {
        super(TypeDocument.class);
    }
    
}
