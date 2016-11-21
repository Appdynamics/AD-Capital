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

import javax.persistence.EntityManager;

/**
 * Created by swetha.ravichandran on 8/13/15.
 */
public class EntityManagerHelper {
    private static ThreadLocal<EntityManager> localRef = new ThreadLocal<EntityManager>();
    private static final EntityManagerHelper instance = new EntityManagerHelper();

    private EntityManagerHelper() {

    }

    public static EntityManagerHelper getInstance() {
        return instance;
    }

    public EntityManager getEntityManager() {
        return localRef.get();
    }

    public void setEntityManager(EntityManager em) {
        localRef.set(em);
    }
}
