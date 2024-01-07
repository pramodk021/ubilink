package com.ubilink.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

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
import com.ubilink.model.Loyaltypoint;
import com.ubilink.model.Maployaltypointuser;
import com.ubilink.model.User;
import com.ubilink.service.SocialMediaService;

@Controller
@RequestMapping(value = "/requestSocialMedia")
public class SocialMediaController {
	private static final Logger logger = Logger.getLogger(SocialMediaController.class);
	@Autowired
	private SocialMediaService socialMediaService;

	@RequestMapping(value = "/shareOffer", method = RequestMethod.GET)
	public ResponseEntity<String> shareOffer(@RequestParam(value = "userId", required = false) int userId,
			@RequestParam(value = "socialAction", required = true) String socialAction) throws Exception {
		logger.debug("Requested shareOffer to add loyalty points to userId = " + userId + " for " + socialAction);
		String result = "failed";
		Loyaltypoint loyaltypoint = null;
		Maployaltypointuser maployaltypointuser = new Maployaltypointuser();
		if (socialAction.equals("shareOfferWithFriends")) {
			loyaltypoint = socialMediaService.findLoyaltypointById(1);
		} else if (socialAction.equals("inviteFriend")) {
			loyaltypoint = socialMediaService.findLoyaltypointById(2);
		}
		maployaltypointuser.setLoyaltypoint(loyaltypoint);
		User user = new User();
		user.setId(userId);
		maployaltypointuser.setUser(user);
		maployaltypointuser.setCreatedOn(new Date());

		try {
			maployaltypointuser = socialMediaService.create(maployaltypointuser);
			LoyaltyPoint loyaltyPoint = new LoyaltyPoint();
			loyaltyPoint.setCurrentPoints(loyaltypoint.getPoints() + "");
			loyaltyPoint.setTotalPoints(socialMediaService.getLoyatyPoints(userId) + "");
			result = writeLoyaltyPointToJsonObject(loyaltyPoint);

		} catch (Exception exception) {
			logger.error(
					"Exception in shareOffer to add loyalty points to userId = " + userId + " for " + socialAction);
			exception.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");

		logger.info("Result of shareOffer to add loyalty points to userId " + userId + " for " + socialAction + " : "
				+ result);

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMyLPs", method = RequestMethod.GET)
	public ResponseEntity<String> getMyTotalLPs(@RequestParam(value = "userId", required = false) int userId)
			throws Exception {
		logger.debug("Requested getMyTotalLPs for userId " + userId);
		String result = "failed";
		try {
			if (socialMediaService.getLoyatyPoints(userId) != null) {
				result = socialMediaService.getLoyatyPoints(userId) + "";
			} else
				result = "0";
		} catch (Exception exception) {
			logger.error("Exception in getMyTotalLPs for userId " + userId);
			exception.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
		logger.info("Requested getMyTotalLPs for userId " + userId);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	public String writeLoyaltyPointToJsonObject(LoyaltyPoint loyaltyPoint) throws IOException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, loyaltyPoint);
		final byte[] data = out.toByteArray();
		// System.out.println(new String(data));
		return new String(data);
	}

	public static class LoyaltyPoint implements Serializable {
		private String currentPoints;
		private String totalPoints;

		public String getCurrentPoints() {
			return this.currentPoints;
		}

		public void setCurrentPoints(String currentPoints) {
			this.currentPoints = currentPoints;
		}

		public String getTotalPoints() {
			return this.totalPoints;
		}

		public void setTotalPoints(String totalPoints) {
			this.totalPoints = totalPoints;
		}

	}
}
