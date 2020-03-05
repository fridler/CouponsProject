package com.yair.coupons.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yair.coupons.entities.Customer;

@Repository
public interface ICustomerDao extends CrudRepository<Customer, Long>{
	
//	public List<Customer> getAllCustomers();
	
	public List<Customer> getCustomersByName(@Param("name") String name);
	
	public List<Customer> getCustomersByAddress(@Param("address") String address);

}
