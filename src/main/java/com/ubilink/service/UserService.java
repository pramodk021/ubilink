package com.ubilink.service;

import java.util.List;

import com.ubilink.exception.UserNotFound;
import com.ubilink.model.User;
import com.ubilink.model.Userstatus;
import com.ubilink.model.Usertype;

public interface UserService 
{
	public User create(User user);
	public User delete(int id) throws UserNotFound;
	public List<User> findAll();
	public User update(User user) throws UserNotFound;
	public User findById(int id);
	public User getUserByMobile(String mobile,Userstatus userstatus);
	public User getUserByMobile(String mobile);
	public User getUserByEmail(String email);
	public User getUserByEmail(String email,Userstatus userstatus);
	public User getUserByMobileAndStatus(String mobile,Userstatus userstatus);
	public User getUserByMobileAndPasswordAndUsertype(String  mobile,String password,Userstatus userstatus,Usertype usertype);
	public User getUserByEmailAndPasswordAndUsertype(String  email,String password,Userstatus userstatus,Usertype usertype);
	public User findUserByFBId(String fbId);
	public User findByEmailOrMobile(String email,String mobile);
}
