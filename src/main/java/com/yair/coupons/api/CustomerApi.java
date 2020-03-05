package com.yair.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yair.coupons.entities.Customer;
import com.yair.coupons.entities.Registration;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.logic.CustomerController;
import com.yair.coupons.logic.UserController;

@RestController
@RequestMapping("/customer")
public class CustomerApi {
	
	@Autowired
	private CustomerController customerController;

//	http://localhost:8080/customer/2
	@GetMapping("/{customerId}")
	public Customer getCustomer(@PathVariable("customer") long customerId) throws Exception {
		System.out.println(customerId);
		return this.customerController.getCustomerById(customerId);
	}
	
//	http://localhost:8080/customer/3
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") long customerId) throws Exception {
		this.customerController.deleteCustomer(customerId);
		System.out.println("Delete : " + customerId);
	}
	
	@PostMapping
	public void createCustomer(@RequestBody Customer customer) throws ApplicationException {
		this.customerController.createCustomer(customer);
		System.out.println("Create: " + customer);
	}
	
//	http://localhost:8080/customer/register
	@PostMapping("/register")
	public void registerCustomer(@RequestBody Registration registration) throws Exception {
		customerController.registerCustomer(registration);
	}
	
	
	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException {
		this.customerController.updateCustomer(customer);
		System.out.println("Update: " + customer);
	}
	
//	http://localhost:8080/customer
	@GetMapping
	public List<Customer> getAllCustomers() throws ApplicationException{
		return customerController.getAllCustomers();
	}
}
