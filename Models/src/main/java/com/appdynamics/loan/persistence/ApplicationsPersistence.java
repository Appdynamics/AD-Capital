package com.appdynamics.loan.persistence;

import com.appdynamics.loan.model.Applications;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

/**
 * Created by swetha.ravichandran on 8/17/15.
 */
public class ApplicationsPersistence extends BasePersistenceImpl {

    private static final Logger log = Logger.getLogger(ApplicationsPersistence.class.getName());


    /**
     * Get applications with "CC" status
     * @return
     */
    public Applications getApplicationsWithCCStatus() {
        Query q = getEntityManager().createQuery("SELECT a from Applications a WHERE a.applicationStatus = 'CC'");
        List<Applications> applicationsList = (List<Applications>) q.getResultList();
        return ((applicationsList == null || applicationsList.size() == 0) ? null : applicationsList.get(0));
    }

    /**
     * Get applications with "UW" status
     * @return
     */
    public Applications getApplicationsWithUWStatus() {

        Query q = getEntityManager().createQuery("SELECT a from Applications a WHERE a.applicationStatus = 'UW'");
        List<Applications> applicationsList = (List<Applications>) q.getResultList();
        return ((applicationsList == null || applicationsList.size() == 0) ? null : applicationsList.get(0));
    }

    /**
     * Updates Application By Id
     * @param id
     * @return
     */
    public Integer updateApplicationsById(String id){
        EntityManager em = getEntityManager();
        EntityTransaction txn = em.getTransaction();
        txn.begin();
        Query q = getEntityManager().createQuery("UPDATE Applications a SET a.applicationStatus = 'UW' where a.id = :id");
        int updatedCount = q.setParameter("id",id).executeUpdate();
        txn.commit();
        return updatedCount;
    }

    /**
     * Deletes application by Id
     * @param id
     * @return
     */
    public Integer deleteApplicationsByID(String id){
        EntityManager em = getEntityManager();
        EntityTransaction txn = em.getTransaction();
        txn.begin();
        Query q = getEntityManager().createQuery("DELETE FROM Applications a WHERE a.id = :id");
        int deletedCount = q.setParameter("id",id).executeUpdate();
        txn.commit();
        return deletedCount;
    }

}
