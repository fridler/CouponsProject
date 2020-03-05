package com.yair.coupons.logic;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.yair.coupons.cache.ICacheController;
import com.yair.coupons.dao.ICompanyDao;
import com.yair.coupons.dao.ICustomerDao;
import com.yair.coupons.dao.IUserDao;
import com.yair.coupons.data.LoggedInUserData;
import com.yair.coupons.data.LoginResponseDataObject;
import com.yair.coupons.data.SuccessfulLoginServerResponse;
import com.yair.coupons.entities.Company;
import com.yair.coupons.entities.Customer;
import com.yair.coupons.entities.User;
import com.yair.coupons.enums.ErrorTypes;
import com.yair.coupons.enums.UserType;
import com.yair.coupons.exceptions.ApplicationException;

@Controller
public class UserController {

	@Autowired
	private ICacheController cachController;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICustomerDao customerDao;
	
//	@Value("${maxFailedLogin}")
//	private int maxOfFailedLogin;
	

	
	public LoginResponseDataObject login(String email, String password) throws ApplicationException {

//		׳�׳•׳�׳™ ׳�׳©׳ ׳•׳× ׳�LOGIN 
		User user = userDao.findUserByEmailAndPassword(email, password);

		if(user == null) {
			throw new ApplicationException(ErrorTypes.LOGIN_FAILED, "Login failed. Please try again.");
		} 

		long userId = user.getUserId();
		
		LoggedInUserData loggedInUserData; 
		
		UserType userType = user.getType();
		Company company = user.getCompany();
		Customer customer = user.getCustomer();

		
        switch (userType) {
        case ADMIN:
			loggedInUserData = new LoggedInUserData(userType, (long) 0, userId, 0);
			break;
        case CUSTOMER:
            loggedInUserData = new LoggedInUserData(userType, (long) 0, userId, customer.getCustomerId());
            break;
        case COMPANY:
            loggedInUserData = new LoggedInUserData(userType, company.getCompanyId(), userId, 0);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + userType);
    }
		
//		if(company != null) {
//			loggedInUserData = new LoggedInUserData(userType, company.getCompanyId(), userId);
//		}else {
//			loggedInUserData = new LoggedInUserData(userType, null, userId);
//		}

		int token = generateToken(email, loggedInUserData);
		loggedInUserData.setToken(token);

		// Converting the int into a String
		String strToken = String.valueOf(token);
		cachController.put(strToken, loggedInUserData);

        LoginResponseDataObject loginResponseDataObject;
        if (userType == UserType.CUSTOMER) {
            loginResponseDataObject = new LoginResponseDataObject(token, loggedInUserData.getUserType(), userId, -1,customer.getCustomerId());
        } else if(userType == UserType.COMPANY) {
            loginResponseDataObject = new LoginResponseDataObject(token, loggedInUserData.getUserType(), userId, company.getCompanyId(), 0);
        }else {
            loginResponseDataObject = new LoginResponseDataObject(token, loggedInUserData.getUserType(), userId, -1, 0);
        }
        return loginResponseDataObject;
		
//		SuccessfulLoginServerResponse loginResponse = new SuccessfulLoginServerResponse(loggedInUserData.getUserType(), token);
//
//		return loginResponse;
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("This is the max number of login tries " /*+ maxOfFailedLogin*/);
	}
	
	private int generateToken(String email, LoggedInUserData loggedInUserData) {
		
		Random rnd = new Random();
		String salt = "#####";
		int token = (email + rnd.nextInt(9999999) + salt + loggedInUserData.getUserId()).hashCode();
		
		return token;
		
		
	}

	
//	public UserType login(String email, String password) throws ApplicationException {
//		return userDao.login(email, password);
//	}


	public long createUser (User user) throws ApplicationException {
		
		validateUserInsert(user);
		userDao.save(user);
		return user.getUserId();
		
	} 
	
	public void updateUser (User user) throws ApplicationException {
		
		validateUserInsert(user);
		userDao.save(user);
		
	}
	
	@Transactional
	public void deleteUser (long userId) throws ApplicationException {
		
		if (customerDao.findById(userId).get() != null) {
			customerDao.deleteById(userId);
			return;
		}
		
		userDao.deleteById(userId);
		
	}
	
	public User getUserById (long userId) throws ApplicationException {
		return userDao.findById(userId).get();
	}
	
	public List <User> getAllUsers () throws ApplicationException{
		return (List<User>) userDao.findAll();
	}
	
    public User getUserByEmail(String email) throws ApplicationException {
        validateEmail(email);
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "UserName doesn't exist");
        }
        return user;
    }

    public void validateEmail(String email) throws ApplicationException {

		if (email == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Email is null");
		}
		if (email.isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Email is empty");
		}
    }

	private void validateUserInsert (User user) throws ApplicationException {
	
		if (user.getEmail() == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Email is null");
		}
		if (user.getEmail().isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Email is empty");
		}
//		if (userDao.findByUserEmail(user.getEmail()) != null) {
//			throw new ApplicationException(ErrorTypes.CUSTOMER_EMAIL_ALREADY_EXISTS, "Email exists in the system");
//		}
		
		
		if (user.getPassword() == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Password is null");
		}
		if (user.getPassword().isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Password is empty");
		}
		if (user.getPassword().length() <= 4) {
			throw new ApplicationException(ErrorTypes.PASSWORD_IS_SHORT, "password must be at least 4 characters");
		}
		
		
		if (user.getType().name() == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Type is null");
		}
		if (user.getType().name().isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Type is empty");
		}
		if (user.getType() == UserType.COMPANY) {
			if (user.getCompany() == null || companyDao.getCompanyByName(user.getCompany().getName()) == null) {
				throw new ApplicationException(ErrorTypes.COMPANY_ID_DOES_NOT_EXISTS, "A user of type 'COMPANY' must be linked to an existing company");
			}
		}
		
	}
}


