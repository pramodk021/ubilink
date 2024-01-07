package com.ubilink.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.exception.UserNotFound;
import com.ubilink.model.User;
import com.ubilink.model.Userstatus;
import com.ubilink.model.Usertype;
import com.ubilink.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	Logger logger=Logger.getLogger(UserServiceImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	@Transactional
	public User create(User user) {
		logger.debug("Entered in create(User user)"+user.toString());
			
		User userCreated=userRepository.save(user);
		//int userId=userCreated.getId();
		//userRepository.flush();
		return userCreated;
	}
	
	@Override
	@Transactional
	public User findById(int id) {
		logger.debug("Entered in findById(int id) id "+id);
		return userRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=UserNotFound.class)
	public User delete(int id) throws UserNotFound {
		User deletedUser = userRepository.findOne(id);
		
		if (deletedUser == null)
			throw new UserNotFound();
		
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=UserNotFound.class)
	public User update(User user) throws UserNotFound {
		User updatedUser = userRepository.findOne(user.getId());
		
		if (updatedUser == null)
			throw new UserNotFound();
		
		updatedUser.setUserstatus(user.getUserstatus());
		return updatedUser;
	}

	/*public int getMaxUserId()
	{
		int maxUserId=-1;
		//maxUserId=userRepository.getCount();
		return maxUserId;
	}*/
	
	public User getUserByMobile(String mobile,Userstatus userstatus)
	{
		logger.debug("Entered in getUserByMobile(String mobile,Userstatus userstatus)");
		User user=userRepository.getUserByMobile(mobile,userstatus);
		return user;
	}
	public User getUserByMobile(String mobile)
	{
		logger.debug("Entered in getUserByMobile(String mobile)");
		User user=userRepository.getUserByMobile(mobile);
		return user;
	}
	public User getUserByEmail(String email,Userstatus userstatus)
	{
		logger.debug("Entered in getUserByEmail(String email,Userstatus userstatus)");
		User user=userRepository.getUserByEmail(email,userstatus);
		return user;
	}
	
	public User getUserByMobileAndPasswordAndUsertype(String mobile,String password,Userstatus userstatus,Usertype userType)
	{
		logger.debug("Entered in getUserByMobileAndPasswordAndUsertype "+"mobile = "+mobile+"Password== "+ password+"User Status "+userstatus.getId()+"User type"+userType.getId());
		User user=userRepository.getUserByMobileAndPasswordAndUsertype(mobile,password,userstatus,userType);
		return user;
	}
	
	public User getUserByEmailAndPasswordAndUsertype(String  email,String password,Userstatus userstatus,Usertype userType)
	{
		logger.debug("Entered in getUserByEmailAndPassword "+"Email = "+email+"Password== "+ password+"User Status "+userstatus.getId()+"User type"+userType.getId());
		User user=userRepository.getUserByEmailAndPasswordAndUsertype(email,password,userstatus,userType);
		return user;
	}

	@Override
	public User getUserByMobileAndStatus(String mobile, Userstatus userstatus) {
		logger.debug("Entered in getUserByMobileAndStatus "+"mobile = "+mobile+"User Status "+userstatus.getId());
		User user=userRepository.getUserByMobileAndStatus(mobile,userstatus);
		return user;
	}

	@Override
	public User getUserByEmail(String email) 
	{
		logger.debug("Entered in getUserByEmail(String email)");
		User user=userRepository.getUserByEmail(email);
		return user;
	}

	@Override
	public User findUserByFBId(String FBId) 
	{
		return userRepository.findByFBId(FBId);
	}

	@Override
	public User findByEmailOrMobile(String email, String mobile) 
	{
		return userRepository.findByEmailOrMobile(email, mobile);
	}
	
}
