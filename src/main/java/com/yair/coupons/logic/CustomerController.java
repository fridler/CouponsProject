package com.yair.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yair.coupons.dao.ICustomerDao;
import com.yair.coupons.dao.IUserDao;
import com.yair.coupons.entities.Customer;
import com.yair.coupons.entities.Registration;
import com.yair.coupons.entities.User;
import com.yair.coupons.enums.ErrorTypes;
import com.yair.coupons.enums.UserType;
import com.yair.coupons.exceptions.ApplicationException;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private IUserDao userDao;
	
	public void registerCustomer(Registration registration) throws  Exception {
	
		customerDao.save(registration.getCustomer());
		
		registration.getUser().setCustomer(registration.getCustomer());
		registration.getUser().setType(UserType.CUSTOMER);
		
		User user = registration.getUser();
		userDao.save(user);
		//userDao.save(registration.getUsers());
	}

	public long createCustomer(Customer customer) throws ApplicationException{

		validateCustomer(customer);
		customerDao.save(customer);
		return customer.getCustomerId();
	}

	public void updateCustomer(Customer customer) throws ApplicationException{

		validateCustomer(customer);
		customerDao.save(customer);
	}
	
	public void deleteCustomer(long customerId) throws Exception{

        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            throw new Exception("Customer does not exist");
        }
		customerDao.deleteById(customerId);
	}
	
	public Customer getCustomerById(long customerId) throws Exception{
		
        Customer customer = customerDao.findById(customerId).get();
        if (customer == null) {
            throw new Exception("Customer does not exist");
        }
        return customer;
//		return customerDao.findById(customerId).get();
	}

	public List <Customer> getAllCustomers (){
		
		return (List<Customer>) customerDao.findAll();
	}

	private void validateCustomer(Customer customer) throws ApplicationException{
		
		if (customer.getName()==null){
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Null customer name");
		}
		if  (customer.getName().isEmpty()){
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Customer name is empty");
		}
		if (customer.getAddress() == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD,"Null address");
		}
		if (customer.getAddress().isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD,"An empty address");
		}
		
	}
	
}


