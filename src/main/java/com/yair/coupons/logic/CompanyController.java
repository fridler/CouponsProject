package com.yair.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yair.coupons.dao.ICompanyDao;
import com.yair.coupons.dao.IUserDao;
import com.yair.coupons.entities.Company;
import com.yair.coupons.entities.RegisterCompany;
import com.yair.coupons.entities.User;
import com.yair.coupons.enums.ErrorTypes;
import com.yair.coupons.enums.UserType;
import com.yair.coupons.exceptions.ApplicationException;

@Controller
public class CompanyController {

	// --------------------- PROPERTIES ---------------------
	@Autowired
	private ICompanyDao companyDao;
    @Autowired
    private IUserDao userDao;

	// --------------------- FUNCTIONS ---------------------
	public void registerCompany(RegisterCompany registerCompany) throws ApplicationException{

        companyDao.save(registerCompany.getCompany());

        registerCompany.getUser().setCompany(registerCompany.getCompany());
        registerCompany.getUser().setType(UserType.COMPANY);

        User user = registerCompany.getUser();
        userDao.save(user);
		
		
//		validateCompanyInsert(company);
//		companyDao.save(company);
//		
//		// Reaching here means that we've survived all the validations, and so we can go and create the company
//		return company.getCompanyId();

	}

	public void updateCompany(Company company) throws ApplicationException{

		validateCompanyInsert(company);
		companyDao.save(company);

	}

	public void deleteCompany(long companyId) throws ApplicationException{
		
		
		if (!companyDao.existsById(companyId)) {
			throw new ApplicationException(ErrorTypes.COMPANY_ID_DOES_NOT_EXISTS, "Company id does not exist");
		}
		
		companyDao.deleteById(companyId);
		
	}
	
	public Company getCompany(long companyId) throws ApplicationException{

		return companyDao.findById(companyId).get();
		
	}
	
    public List<Company> getCompanyByName(String name) throws Exception{
		List<Company> companies = companyDao.getCompanyByName(name);
		if(companies.size() == 0){
			throw new Exception("Company does not exist");
		}
		return companies;
    }

	public List <Company> getAllCompanies() throws ApplicationException{
		
		return (List<Company>) companyDao.findAll();
	}

	 public void deleteAllCompanies() throws Exception {
	        companyDao.deleteAll();
	    }
	// --------------------- VALIDATIONS ---------------------
	private void validateCompanyInsert(Company company) throws ApplicationException{
		
		if (company.getName() == null){
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Null company name.");
		}

		if  (company.getName().isEmpty()){
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Company Name field is empty.");
		}
		
		if (company.getAddress() == null){
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Null company address.");
		}

		if  (company.getAddress().isEmpty()){
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Company address field is empty.");
		}
		
//		if (companyDao.findByCompanyName(company.getName()) != null) {
//			throw new ApplicationException(ErrorTypes.COMPANY_NAME_IS_ALREADY_EXISTS, "The company name already exists");
//		}
		
	}
}

