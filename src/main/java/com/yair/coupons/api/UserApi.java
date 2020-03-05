package com.yair.coupons.api;

import java.util.List;

import org.hibernate.HibernateException;
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

import com.yair.coupons.data.LoginResponseDataObject;
import com.yair.coupons.data.SuccessfulLoginServerResponse;
import com.yair.coupons.data.UserLoginDetailsDataObject;
import com.yair.coupons.entities.User;
import com.yair.coupons.enums.UserType;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.exceptions.UserNotFoundException;
import com.yair.coupons.logic.UserController;

@RestController
@RequestMapping("/user")
public class UserApi {

	@Autowired
	private UserController userController;
	private User currentLoggedInUser;

	public User getCurrentLoggedInUser() {
		return currentLoggedInUser;
	}
	
//	http://localhost:8080/user/login
	@PostMapping
	@RequestMapping("/login")
	public LoginResponseDataObject login(@RequestBody UserLoginDetailsDataObject userLoginDetailsDataObject) throws HibernateException, UserNotFoundException{

		String email = userLoginDetailsDataObject.getEmail();
		String password = userLoginDetailsDataObject.getPassword();
		
		LoginResponseDataObject loginResponseDataObject = userController.login(email, password);

		return loginResponseDataObject;
	}

	/*
	http://localhost:8080/user
	CREATE USER ONLY WITHOUT COMPANYID
		{
	      "email":"aa@aa.aa",
	      "password":"AAAAAAA1",
	      "type":"ADMIN"
	}
	 */
	@PostMapping
	public long createUser(@RequestBody User user) throws ApplicationException{
		return userController.createUser(user);
	}
	
//	http://localhost:8080/user/3
	@PutMapping
	public void updateUser(@RequestBody User user) throws ApplicationException{
		userController.updateUser(user);
	}

//	http://localhost:8080/user/getUser/3
	@GetMapping
	@RequestMapping("/getUser/{userId}")
	public User getUserById(@PathVariable("userId") long userId) throws ApplicationException{
		return userController.getUserById(userId);
	}
	
//	http://localhost:8080/user/3
	@DeleteMapping
	@RequestMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long userId) throws ApplicationException{
		userController.deleteUser(userId);
	}
//	http://localhost:8080/user/
	@GetMapping
	public List<User> getAllUsers() throws ApplicationException{
		return userController.getAllUsers();
	}
	
//	http://localhost:8080/user/byEmail?email=aa@aa.com
	@GetMapping("/byEmail")
	public  User getUserByEmail(@RequestParam("email") String email) throws ApplicationException{
		return userController.getUserByEmail(email);
	}
}
