/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamics.loan.persistence;

import java.io.Serializable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by swetha.ravichandran on 8/13/15.
 */
public class BasePersistenceImpl {


    private EntityManagerFactory emf = null;

    private EntityManager em = null;

    private static final int DEFAULT_INTERNAL = 15;

    private int interval = DEFAULT_INTERNAL;
	/**
	 * The method to update the serailizable business objects into the database.
	 * 
	 * @param object --
	 *            serializable object
	 * @throws PersistenceException
	 */
    @Transactional
    public void update(final Serializable object) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        try {
            entityManager.merge(object);
        } catch (Exception ex) {
            txn.rollback();
        } finally {
            if (!txn.getRollbackOnly()) {
                txn.commit();
            }
        }
    }


    public EntityManagerFactory getEntityManagerFactory() {
        return this.emf;
    }

    public void setEntityManagerFactory(EntityManagerFactory factory) {
         this.emf = factory;
    }


    public EntityManager getEntityManager() {
           return EntityManagerHelper.getInstance().getEntityManager();
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
	public void save(final Serializable object) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        try {
            entityManager.persist(object);
        } catch (Exception ex) {
            txn.rollback();
        } finally {
            if (!txn.getRollbackOnly()) {
                txn.commit();
            }
        }

	}

    @Transactional
    public void delete(Serializable object){
        EntityManager entityManager = getEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        try {
            entityManager.remove(object);
        } catch (Exception ex) {
            txn.rollback();
        } finally {
            if (!txn.getRollbackOnly()) {
                txn.commit();
            }
        }
	}

}
