package com.ubilink.controller;

import java.sql.Blob;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubilink.service.ImgService;

@Controller
@RequestMapping(value = "/requestImg")
public class ImageController {
	private static final Logger logger = Logger.getLogger(ImageController.class);
	@Autowired
	public ImgService imgService;

	@ResponseBody
	@RequestMapping(value = "/getImgEvent", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImgEvent(@RequestParam(value = "eventId", required = false) int eventId) {
		logger.debug("requested getImgEvent with eventId=" + eventId);
		return imgService.getEventImgById(eventId);
	}

	@ResponseBody
	@RequestMapping(value = "/getImgPlace", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public Blob getImgPlace(@RequestParam(value = "placeId", required = false) int placeId) {
		logger.debug("requested getImgPlace with placeId=" + placeId);
		return imgService.getPlaceImgById(placeId);
	}

	@ResponseBody
	@RequestMapping(value = "/getImgOffer", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImgOffer(@RequestParam(value = "offerId", required = false) int offerId) {
		logger.debug("requested getImgOffer with offerId=" + offerId);
		return imgService.getOfferImgById(offerId);
	}

	@ResponseBody
	@RequestMapping(value = "/getImgPlaceLogo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImgPlaceLogo(@RequestParam(value = "placeId", required = false) int placeId) {
		logger.debug("requested getImgPlaceLogo with placeId=" + placeId);
		return imgService.getPlaceLogoImg(placeId);
	}

	@ResponseBody
	@RequestMapping(value = "/getImgPlaceCat", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public Blob getImgPlaceCat(@RequestParam(value = "placeCatId", required = true) int placeCatId) {
		logger.debug("requested getImgPlaceCat with placeCatId=" + placeCatId);
		return imgService.getPlaceCatImgById(placeCatId);
	}

	@ResponseBody
	@RequestMapping(value = "/getProdCatImg", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public Blob getProdCatImg(@RequestParam(value = "prodCatId", required = true) int prodCatId) {
		logger.debug("requested getProdCatImg with prodCatId=" + prodCatId);
		return imgService.getProdCatImg(prodCatId);
	}
}
