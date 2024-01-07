package com.ubilink.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public User register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobile = request.getParameter("mobile");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMobile(mobile);
		return user;
	}

	@RequestMapping(value = "/registerview")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/register");
		return mav;
	}
}

class User {

	private String firstName;

	private String lastName;

	private String mobile;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
