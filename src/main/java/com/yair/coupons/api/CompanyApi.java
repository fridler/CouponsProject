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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yair.coupons.dao.ICompanyDao;
import com.yair.coupons.dao.ICustomerDao;
import com.yair.coupons.dao.IUserDao;
import com.yair.coupons.entities.Company;
import com.yair.coupons.entities.RegisterCompany;
import com.yair.coupons.entities.User;
import com.yair.coupons.enums.UserType;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.logic.CompanyController;

@RestController
@RequestMapping("/company")
public class CompanyApi {
	
	@Autowired
	private CompanyController companyController;
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private IUserDao userDao;
	
//  http://localhost:8080/company/5
	@GetMapping("/{companyId}")
	public Company getCompany(@PathVariable("companyId") long companyId) throws ApplicationException {
		
		System.out.println(companyId);
		return this.companyController.getCompany(companyId);
	}
	
	//http://localhost:8080/company/1
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long companyId) throws ApplicationException {
		this.companyController.deleteCompany(companyId);
		System.out.println("Delete: " + companyId);
	}
	
	@PutMapping
	public void updateCompany(@RequestBody Company company) throws ApplicationException {
		this.companyController.updateCompany(company);
		System.out.println("Update: " + company);
	}
	
	//http://localhost:8080/company/register
	@PostMapping("/register")
	public void registerCompany(@RequestBody RegisterCompany registerCompany) throws ApplicationException {
	
		this.companyController.registerCompany(registerCompany);
		System.out.println("Create: " + registerCompany);
	}
	
	//    http://localhost:8080/company
	@GetMapping
	public List<Company> getAllCompanies() throws Exception {
		return companyController.getAllCompanies();
	}
	
	//    http://localhost:8080/company/byName?name=apple
	@GetMapping("/byName")
	public List<Company> getCompanyByName(@RequestParam("name") String name) throws Exception {
		return companyController.getCompanyByName(name);
	}
	
	//    http://localhost:8080/company
	// Delete all Companies from Company and the associated Users from User and Coupons from Coupon
	@DeleteMapping
	public void deleteAllCompanies() throws Exception {
		companyController.deleteAllCompanies();
	}
}
