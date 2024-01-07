package com.ubilink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.User;
import com.ubilink.model.Userstatus;
import com.ubilink.model.Usertype;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	/*@Query("SELECT max(u.id) FROM User u")
    public int getCount();*/
	public User save(User user);
	
	@Query("SELECT u FROM User u WHERE u.id=?1")
	public User findOne(int id);
	
	@Query("SELECT u FROM User u WHERE u.mobile = ?1 ")
	public User getUserByMobile(String mobile);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1 ")
	public User getUserByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.mobile = ?1 and u.userstatus=?2")
	public User getUserByMobile(String mobile,Userstatus userstatus);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1 and u.userstatus=?2")
	public User getUserByEmail(String email,Userstatus userstatus);
	
	@Query("SELECT u FROM User u WHERE u.mobile = ?1 and u.password=?2 and u.userstatus=?3 and u.usertype=?4")
	public User getUserByMobileAndPasswordAndUsertype(String  mobile,String password,Userstatus userstatus,Usertype usertype );
	
	@Query("SELECT u FROM User u WHERE u.email = ?1 and u.password=?2 and u.userstatus=?3 and u.usertype=?4")
	public User getUserByEmailAndPasswordAndUsertype(String email,String password,Userstatus userstatus,Usertype usertype);
	
	@Query("SELECT u FROM User u WHERE u.mobile = ?1 and u.userstatus=?2")
	public User getUserByMobileAndStatus(String  mobile,Userstatus userstatus);
	
	@Query("SELECT u FROM User u WHERE u.FBId = ?1")
	public User findByFBId(String FBId);
	
	public User findByEmailOrMobile(String email,String mobile);
	
	
	
}
