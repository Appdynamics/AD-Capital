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

package com.appdynamics.loan.service;


import com.appdynamics.loan.model.Customer;
import com.appdynamics.loan.persistence.CustomerPersistence;


public class CustomerService {

	public CustomerPersistence getCustomerPersistence() {
		return customerPersistence;
	}

	public void setCustomerPersistence(CustomerPersistence customerPersistence) {
		this.customerPersistence = customerPersistence;
	}
	private CustomerPersistence customerPersistence;

	public boolean validateMember(String name,long passcode){
		Customer existingMember = getMemberByName(name);
		if(existingMember==null ||!existingMember.getId().equals(passcode)){
			return false;
		}
		return true;
	}

	public Customer getMemberByName(String email) {
		return customerPersistence.getMemberByName(email);
	}
	public Customer getMemberById(Integer id){
		return customerPersistence.getMemberById(id);
	}
}
