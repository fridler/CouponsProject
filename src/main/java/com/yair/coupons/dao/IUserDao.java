package com.yair.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yair.coupons.entities.User;
import com.yair.coupons.enums.UserType;

@Repository
public interface IUserDao extends CrudRepository<User, Long>{

	/*
	public User findByUserEmail(String email);
	
	public List<User> getAllUsers();
	
	public List<User> getAllUsersByType(UserType type);
	
	public List<User> getAllUsersByEmail(String email);
	
	public List<User> getAllUsersByCompanyId(String companyId);
	*/
	
	@Query("SELECT u FROM User u WHERE u.id=:userId")
	User findByUserId(@Param("userId") Long userId);
	
	@Query("SELECT u FROM User u WHERE u.email= :email AND u.password= :password")
//	@Query("FROM User u WHERE u.email=?:email && u.password=? :password")
	User findUserByEmailAndPassword(@Param("email") String email, @Param("password")  String password);

	@Query("SELECT u FROM User u WHERE u.email=:email AND u.password=:password")
	List<User> findUsersByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
//	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email=? :email")
	//	@Query("SELECT * FROM User u WHERE u.email=?:email")
	boolean existsByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.password=:password")
	List<User> findByPassword(@Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.email=:email")
	User findByEmail(@Param("email") String email);
}


