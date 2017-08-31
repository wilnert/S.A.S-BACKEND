/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sas.jpa.sessions;

import com.sas.jpa.entities.User;
import com.sas.jpa.entities.User_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author darkreaven
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "com.sas_BackendSAS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
     public User findByIdentificationNumber(String identification) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> tercero = cq.from(User.class);

        cq.where(cb.equal(tercero.get(User_.identification),identification ));

        TypedQuery<User> tq = getEntityManager().createQuery(cq);

        try {
            return (User) tq.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw e;
        } catch (NoResultException ex) {
            return null;

        }
    }

    //validaar existencia de un email        
    public User findByEmail(String email) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> tercero = cq.from(User.class);

        cq.where(cb.equal(tercero.get(User_.email), email));

        TypedQuery<User> tq = getEntityManager().createQuery(cq);

        try {
            return (User) tq.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw e;
        } catch (NoResultException ex) {
            return null;

        }
    }
    
    public List<User> findAssistance() {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> usuario = cq.from(User.class);

        Predicate data = cb.conjunction();

            data = cb.and(data, cb.isTrue(usuario.get(User_.active)));
            
        cq.where(data);

        TypedQuery<User> tq = getEntityManager().createQuery(cq);

        try {
            return tq.getResultList();
        } catch (Exception e) {
            return null;
        }

    }
    
}
