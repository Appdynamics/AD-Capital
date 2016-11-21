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

import java.util.List;

import com.appdynamics.loan.model.Customer;
/**
 * Created by swetha.ravichandran on 8/13/15.
 */
import javax.persistence.*;

public class CustomerPersistence extends BasePersistenceImpl {

    /**
     * Get Customer by name
     * @param name
     * @return
     */
    public Customer getMemberByName(String name) {
        Query q = getEntityManager().createQuery("SELECT c from Customer c WHERE c.name=:name");
        q.setParameter("name", name);
        List<Customer> memberList = (List<Customer>) q.getResultList();
        return ((memberList == null || memberList.size() == 0) ? null : memberList.get(0));
    }

    /**
     * Get Customer by id
     * @param id
     * @return
     */
    public Customer getMemberById(Integer id) {
        Query q = getEntityManager().createQuery("SELECT c from Customer c WHERE c.id=:id");
        q.setParameter("id", id);
        List<Customer> memberList = (List<Customer>) q.getResultList();
        return ((memberList == null || memberList.size() == 0) ? null : memberList.get(0));
    }
}
