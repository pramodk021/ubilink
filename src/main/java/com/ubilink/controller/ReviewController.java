package com.ubilink.controller;

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

import com.ubilink.model.Loyaltypoint;
import com.ubilink.model.Maployaltypointuser;
import com.ubilink.model.Mapreview;
import com.ubilink.model.Reviewtype;
import com.ubilink.model.User;
import com.ubilink.service.MapReviewService;
import com.ubilink.service.ReviewTypeService;
import com.ubilink.service.SocialMediaService;

@Controller
@RequestMapping(value = "/requestReview")
public class ReviewController {
	private static final Logger logger = Logger.getLogger(ReviewController.class);
	@Autowired
	public MapReviewService mapReviewService;
	@Autowired
	public ReviewTypeService reviewTypeService;
	@Autowired
	private SocialMediaService socialMediaService;

	@RequestMapping(value = "/createReview", method = RequestMethod.POST)
	public ResponseEntity<String> getProductCategories(@RequestParam(value = "userId", required = false) int userId,
			@RequestParam(value = "reviewType", required = false) String reviewType,
			@RequestParam(value = "entityId", required = true) int entityId,
			@RequestParam(value = "review", required = true) String review) {
		logger.debug("requested getProductCategories()");
		String result = "failed";
		try {
			Reviewtype reviewTypeObj = reviewTypeService.findIdByType(reviewType);
			User user = new User();
			user.setId(userId);
			if (reviewType.equals("place")
					&& mapReviewService.findByReviewtypeAndUserAndEntityId(reviewTypeObj, user, entityId) == null) {
				Maployaltypointuser maployaltypointuser = new Maployaltypointuser();
				Loyaltypoint loyaltypoint = socialMediaService.findLoyaltypointByRule("firstTimePlaceReview");
				maployaltypointuser.setLoyaltypoint(loyaltypoint);
				maployaltypointuser.setUser(user);
				maployaltypointuser.setCreatedOn(new Date());
				socialMediaService.create(maployaltypointuser);
			}
			Mapreview mapreview = new Mapreview();
			mapreview.setEntityId(entityId);
			mapreview.setUser(user);
			mapreview.setReviewtype(reviewTypeObj);
			mapreview.setReview(review);
			mapreview.setCreatedOn(new Date());
			mapreview = mapReviewService.create(mapreview);
			if (mapreview.getId() > 0)
				result = "Review created successfully";
		} catch (Exception e) {
			logger.error("Exception in getProductCategories ");
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
		logger.info("Result of getProductCategories : " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

}