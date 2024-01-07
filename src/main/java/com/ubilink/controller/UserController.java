package com.ubilink.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubilink.model.User;
import com.ubilink.model.Userstatus;
import com.ubilink.model.Usertype;
import com.ubilink.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<String> validateUser(
			@RequestParam(value = "mobileOREmail", required = true) String mobileOREmail,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "loginMode", required = true) String loginMode) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
		// User invalidUser = new User();
		// invalidUser.setId(-1);
		String result = "Invalid data";
		try {

			logger.info("requested validate User with details email ,mobile ,loginMode : " + mobileOREmail + ", "
					+ password + ", " + loginMode);

			if ((mobileOREmail == null || mobileOREmail.equalsIgnoreCase(""))
					&& (password == null || password.equalsIgnoreCase(""))) {
				result = "Invalid data";
				return new ResponseEntity<String>(result, responseHeaders, HttpStatus.NOT_FOUND);
			}
			Userstatus userstatus = new Userstatus();
			userstatus.setId(3);
			Usertype usertype = new Usertype();
			usertype.setId(1);
			User user = null;
			if (loginMode.equals("SYS")) {
				if ((mobileOREmail != null || !mobileOREmail.equalsIgnoreCase(""))
						&& (password != null || !password.equalsIgnoreCase(""))) {
					user = userService.getUserByEmailAndPasswordAndUsertype(mobileOREmail, password, userstatus,
							usertype);
					if (user != null) {
						result = writeUserToJsonObject(user);
						return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
					} else {
						user = userService.getUserByMobileAndPasswordAndUsertype(mobileOREmail, password, userstatus,
								usertype);
						if (user != null) {
							result = writeUserToJsonObject(user);
							return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
						}
					}
				}
			}
			if (loginMode.equalsIgnoreCase("FB")) {
				user = userService.findUserByFBId(mobileOREmail);
				if (user != null) {
					result = writeUserToJsonObject(user);
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("login failed:= " + mobileOREmail);
		}
		logger.info("Result of validateUser : " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/validateAdminUser", method = RequestMethod.GET)
	public ResponseEntity<String> validateAdminUser(
			@RequestParam(value = "mobileOREmail", required = true) String mobileOREmail,
			@RequestParam(value = "password", required = false) String password) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
		String result = "Invalid credentials";
		try {
			logger.info(
					"requested validateAdminUser with details email/mobile" + mobileOREmail + "password : " + password);
			if ((mobileOREmail == null || mobileOREmail.equalsIgnoreCase(""))
					|| (password == null || password.equalsIgnoreCase(""))) {
				result = "Invalid credentials";
				return new ResponseEntity<String>(result, responseHeaders, HttpStatus.NOT_FOUND);
			}
			Userstatus userstatus = new Userstatus();
			userstatus.setId(3);
			Usertype usertype = new Usertype();
			usertype.setId(2);
			User user = null;
			user = userService.getUserByEmailAndPasswordAndUsertype(mobileOREmail, password, userstatus, usertype);
			if (user != null) {
				result = writeUserToJsonObject(user);
				return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
			} else {
				user = userService.getUserByMobileAndPasswordAndUsertype(mobileOREmail, password, userstatus, usertype);
				if (user != null) {
					result = writeUserToJsonObject(user);

					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();

			logger.error("login failed:= " + mobileOREmail);
		}
		logger.info("Result of validateUser : " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "facebookId", required = false) String facebookId,
			@RequestParam(value = "registerMode", required = true) String registerMode,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "name", required = false) String name) {

		logger.info("requested to registerUser with email, mobile, password, isPassRequired, name : " + email + " ,"
				+ mobile + " ," + password + " ," + name);
		if (email != null && email.equalsIgnoreCase("")) {
			email = null;
		}

		if (mobile != null && mobile.equalsIgnoreCase("")) {
			mobile = null;
		}
		if (facebookId != null && facebookId.equalsIgnoreCase("")) {
			facebookId = null;
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		String result = "failed";
		try {
			User userbyFB = (facebookId != null && !facebookId.equalsIgnoreCase(""))
					? userService.findUserByFBId(facebookId)
					: null;
			User userbyEmail = email != null && !email.equalsIgnoreCase("") ? userService.getUserByEmail(email) : null;
			User userbyMobile = mobile != null && !mobile.equalsIgnoreCase("") ? userService.getUserByMobile(mobile)
					: null;

			if (registerMode.equalsIgnoreCase("FB")) {
				if (facebookId == null || facebookId.equalsIgnoreCase("")) {
					result = "FacebookId can not be null";
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);

				}
				if (userbyFB != null) {
					result = writeUserToJsonObject(userbyFB);
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
				}

				if (userbyEmail != null) {
					userbyEmail.setFBId(facebookId);
					User updatedUser = userService.update(userbyEmail);

					if (updatedUser != null) {
						result = writeUserToJsonObject(updatedUser);
						return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
					} else {
						result = "Already registered";
						return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);
					}

				}

				if (userbyMobile != null) {
					userbyMobile.setFBId(facebookId);
					User updatedUser = userService.update(userbyMobile);

					if (updatedUser != null) {
						result = writeUserToJsonObject(updatedUser);
						return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
					} else {
						result = "Already registered";
						return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);
					}

				}

				if ((userbyEmail != null) || (userbyMobile != null)) {

					result = "Already registered";

					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);

				} else {

					User user = new User(mobile, password, email, name, facebookId);
					Usertype usertype = new Usertype();
					usertype.setId(1);
					Userstatus userstatus = new Userstatus();
					userstatus.setId(3);
					// user.setMall(place);
					user.setUserstatus(userstatus);
					user.setUsertype(usertype);
					User createdUser = userService.create(user);
					if (createdUser != null)
						result = writeUserToJsonObject(createdUser);
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
				}
			}
			if (registerMode.equalsIgnoreCase("SYS")) {

				if ((userbyEmail != null) || (userbyMobile != null)) {
					result = "Already registered";
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);
				}

				if (password == null || password.equalsIgnoreCase("")) {
					result = "Enter password";
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);
				}
				if ((email == null || email.equalsIgnoreCase("")) && (mobile == null || mobile.equalsIgnoreCase(""))) {
					result = "Enter Either email or mobile";
					return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);
				}

				User user = new User(mobile, password, email, name, facebookId);
				Usertype usertype = new Usertype();
				usertype.setId(1);
				Userstatus userstatus = new Userstatus();
				userstatus.setId(3);
				// user.setMall(place);
				user.setUserstatus(userstatus);
				user.setUsertype(usertype);
				User createdUser = userService.create(user);
				if (createdUser != null)
					result = writeUserToJsonObject(createdUser);
				return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
			}

		}

		catch (Exception e) {
			logger.error("Exception into register User with email, mobile, password, isPassRequired, name : " + email
					+ " ," + mobile + " ," + password + " ," + name);
			e.printStackTrace();
		}

		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");

		logger.info("Result of registerUser :" + result);

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CONFLICT);
	}

	public String writeUserToJsonObject(User userCopy) throws IOException {

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();

		try {
			UserSer userSer = new UserSer();
			userSer.setEmail(userCopy.getEmail());
			userSer.setId(userCopy.getId());
			userSer.setName(userCopy.getName());
			if (userCopy.getPlace() != null)
				userSer.setPlaceId(userCopy.getPlace().getId());
			if (userCopy.getUsertype() != null)
				userSer.setUsertypeId(userCopy.getUsertype().getId());

			mapper.writeValue(out, userSer);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		final byte[] data = out.toByteArray();
		return new String(data);
	}

	public static class UserSer implements Serializable {
		private int id;
		private String name;
		private String email;
		private int usertypeId;
		private int placeId;

		public UserSer() {
		}

		public UserSer(int id, String name, String email) {
			this.id = id;
			this.name = name;
			this.email = email;
		}

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getUsertypeId() {
			return usertypeId;
		}

		public void setUsertypeId(int usertypeId) {
			this.usertypeId = usertypeId;
		}

		public int getPlaceId() {
			return placeId;
		}

		public void setPlaceId(int placeId) {
			this.placeId = placeId;
		}

	}

}
