package com.ubilink.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubilink.model.Favoritetype;
import com.ubilink.model.Mapfavourite;
import com.ubilink.model.Offer;
import com.ubilink.model.Place;
import com.ubilink.model.Productcategory;
import com.ubilink.model.User;
import com.ubilink.service.MapfavouriteService;
import com.ubilink.service.OfferService;
import com.ubilink.util.CommonUtilities;
import com.ubilink.util.DateUtil;

@Controller
@RequestMapping(value = "/requestOffers")
public class OfferController {
	private static final Logger logger = Logger.getLogger(OfferController.class);

	@Autowired
	private OfferService offerService;
	@Autowired
	private MapfavouriteService mapfavouriteService;

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ResponseEntity<String> offerList(@RequestParam(value = "userId", required = true) int userId) {
		logger.debug("requested offerList of userId= " + userId);
		List<Offer> offers = null;
		String result = "failed";
		try {
			offers = offerService.findAll();
			result = writeTrackedOfferListToJsonArray(offers, userId);
		} catch (Exception e) {
			logger.error("Exception in offerList");
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
		logger.info("listAll offers " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/detailsById", method = RequestMethod.GET)
	public ResponseEntity<String> offerDetailsById(@RequestParam(value = "offerId", required = true) int offerId,
			@RequestParam(value = "userId", required = true) int userId) {

		logger.debug("requested offerDetailsById with offerId = " + offerId);

		Offer offer = null;
		String result = "failed";

		logger.info("offerDetailsById() offerId = " + offerId);
		try {
			offer = offerService.findById(offerId);
			result = writeObjectToJsonObject(offer, userId);
		} catch (Exception e) {
			logger.error("Exception in offerDetailsById offerId =" + offerId);
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");

		logger.info("offerDetails = " + result);

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/listByPlaceId", method = RequestMethod.GET)
	public ResponseEntity<String> offerListByMall(@RequestParam(value = "placeId", required = true) int placeId,
			@RequestParam(value = "userId", required = true) int userId,
			@RequestParam(value = "pageNum", required = true) int pageNum,
			@RequestParam(value = "pageSize", required = true) int pageSize) {

		logger.debug("requested offerListByMall with placeId = " + placeId);
		List<Offer> offers = null;
		String result = "failed";

		try {
			// offers=offerService.getAllOffersByMall(placeId);
			offers = offerService.findPageByMall(placeId, pageNum, pageSize);
			result = writeListToJsonArray(offers, userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in offerListByMall ,placeId = " + placeId);
		}
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");

		logger.info("Requested offers of placeId " + placeId + " : " + result);

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/listByMallAndCategory", method = RequestMethod.GET)
	public ResponseEntity<String> offerListByMallAndCategory(
			@RequestParam(value = "placeId", required = true) int placeId,
			@RequestParam(value = "categoryId", required = true) int categoryId,
			@RequestParam(value = "userId", required = true) int userId,
			@RequestParam(value = "pageNum", required = true) int pageNum,
			@RequestParam(value = "pageSize", required = true) int pageSize) {

		logger.debug("requested listByMallAndCategory with placeId,categoryId,userId,pageNum,pageSize = " + placeId
				+ " ," + categoryId + " ," + userId + " ," + pageNum + " ," + pageSize + " ");
		List<Offer> offers = null;
		String result = "failed";

		try {
			if (categoryId > 0)
				offers = offerService.findByRetailerMallAndProductcategory(placeId, categoryId, pageNum, pageSize);
			else
				offers = offerService.findPageByMall(placeId, pageNum, pageSize);
			if (offers != null)
				result = writeListToJsonArray(offers, userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in offerListByMall ,placeId = " + placeId);
		}
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "text/plain; charset=UTF-8");

		logger.info("Requested offers of placeId " + placeId + " and categoryId " + categoryId + " : " + result);

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/listByHotspot", method = RequestMethod.GET)
	public ResponseEntity<String> offerListByHotspotAndOfferCategory(
			@RequestParam(value = "hotspotId", required = true) int hotspotId,
			@RequestParam(value = "categoryId", required = true) int productCatId,
			@RequestParam(value = "userId", required = true) int userId,
			@RequestParam(value = "pageNum", required = true) int pageNum,
			@RequestParam(value = "pageSize", required = true) int pageSize) {

		logger.debug("requested offerListByHotspotAndOfferCategory with hotspotId,productCatId,pageNum,pageSize = "
				+ hotspotId + " ," + pageNum + " ," + pageSize + " ," + productCatId);
		List<Offer> offers = null;
		String result = "failed";

		try {
			offers = offerService.findByHotspot(hotspotId, productCatId, pageNum, pageSize);
			if (offers != null)
				result = writeListToJsonArray(offers, userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in offerListByHotspotAndOfferCategory ,hotspotId = " + hotspotId);
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "text/plain; charset=UTF-8");

		logger.debug("Requested offers of hotspotId " + hotspotId + " : " + result);

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/offerPage", method = RequestMethod.GET)
	public ResponseEntity<String> offerPage(@RequestParam(value = "pageNum", required = false) int pageNum,
			@RequestParam(value = "pageSize", required = false) int pageSize,
			@RequestParam(value = "categoryId", required = false) int categoryId,
			@RequestParam(value = "userId", required = false) int userId) {

		logger.debug("requested offerPage with pageNum, pageSize, categoryId, userId " + pageNum + " ," + " ,"
				+ pageSize + " ," + categoryId + " ," + userId);
		List<Offer> offers = null;
		String result = "failed";

		try {
			offers = offerService.findPage(pageNum, pageSize, categoryId).getContent();

			result = writeListToJsonArray(offers, userId);
		} catch (Exception e) {
			logger.error("Exception in offerPage with pageNum, pageSize, categoryId, userId " + pageNum + " ," + " ,"
					+ pageSize + " ," + categoryId + " ," + userId);
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");

		logger.info("result(offerList) of  offerPage = " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/getTrackedOfferList", method = RequestMethod.GET)
	public ResponseEntity<String> getTrackedOfferList(@RequestParam(value = "userId", required = false) int userId) {

		logger.debug("Requested getTrackedOfferList by userId = " + userId);

		List<Offer> offers = null;
		String result = "failed";

		try {
			offers = offerService.findAll();
			result = writeTrackedOfferListToJsonArray(offers, userId);
		} catch (Exception e) {
			logger.error("Exception in getTrackedOfferList by userId = " + userId);
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
		logger.info("Result of getTrackedOfferList by userId = " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProductNames", method = RequestMethod.GET)
	public ResponseEntity<String> getProductNames(@RequestParam(value = "placeId", required = false) int placeId,
			@RequestParam(value = "product", required = false) String product) {

		logger.debug("Requested getProductNames with retailerId = " + placeId);

		String result = "failed";

		try {

			List<String> products = offerService.getProductNames(placeId, product);
			// result=offerService.getProductNames(retailerId, product).toString();

			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			final ObjectMapper mapper = new ObjectMapper();

			mapper.writeValue(out, products);

			final byte[] data = out.toByteArray();
			result = new String(data);
		} catch (Exception e) {
			logger.error("Exception in getProductNames with placeId = " + placeId);
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "Application/json; charset=UTF-8");
		logger.info("Result of getProductNames with retailerId " + placeId + " : " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/createOffer", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<String> createOffer(
			@RequestParam(value = "offerImg", required = false) MultipartFile offerImg,
			@RequestParam(value = "offerName", required = true) String offerName,
			@RequestParam(value = "categoryId", required = true) int categoryId,
			@RequestParam(value = "placeId", required = true) int placeId,
			@RequestParam(value = "validity", required = true) String validity,
			@RequestParam(value = "productName", required = true) String productName,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "validFrom", required = true) String validFrom,
			@RequestParam(value = "userId", required = true) int userId) {
		logger.debug("createOffer requested");
		InputStream inputStream = null;
		Offer offer = new Offer();
		try {
			offer.setName(offerName);
			offer.setDescription(description);
			Productcategory productcategory = new Productcategory();
			productcategory.setId(categoryId);
			offer.setProductcategory(productcategory);

			Place place = new Place();
			place.setId(placeId);
			offer.setPlace(place);
			offer.setCreatedBy(userId);
			offer.setValidity(DateUtil.getDateFromString(validity));
			offer.setValidFrom(DateUtil.getDateFromString(validFrom));
			offer.setProductName(productName);
			if (offerImg != null)
				inputStream = offerImg.getInputStream();
			offer.setImgFile(CommonUtilities.getImageByteArray(inputStream));
			Offer createdOffer = offerService.create(offer);
			System.out.println("Offer created with Id = " + createdOffer.getId());

		} catch (Exception e) {
			logger.error("Exception in createOffer");
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "text/html");
		logger.info(offer.getName() + " has been created successfully!");
		return new ResponseEntity<String>(offer.getName() + " has been created successfully!", responseHeaders,
				HttpStatus.OK);
	}

	public String writeObjectToJsonObject(Offer offer, int userId) throws IOException {

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();

		OfferSer offerSer = new OfferSer();
		offerSer.setId(offer.getId());
		offerSer.setName(offer.getName());
		offerSer.setProductName(offer.getProductName());
		// NTR
		// offerSer.setImgPath(UbiConstants.OFFER_IMAGES+offer.getImgPath()+".png");
		offerSer.setDescription(offer.getDescription());
		if (offer.getImgFile() != null)
			offerSer.setImgURL("" + offer.getId());

		offerSer.setValidFrom(DateUtil.getStringFromDate(offer.getValidFrom()));
		offerSer.setValidity(DateUtil.getStringFromDate(offer.getValidity()));
		offerSer.setPlaceId(offer.getPlace().getId());
		if (offer.getPlace() != null && offer.getPlace().getLogoImg() != null)
			offerSer.setPlaceLogoImg(offer.getPlace().getId() + "");
		Mapfavourite mapfavourite = findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(userId, offer.getId());
		if (mapfavourite != null)
			offerSer.setTracked(mapfavourite.isIsActive());
		mapper.writeValue(out, offerSer);

		final byte[] data = out.toByteArray();
		// System.out.println(new String(data));
		return new String(data);
	}

	public String writeListToJsonArray(List<Offer> offers, int userId) throws IOException {
		List<OfferSer> offfersSer = new ArrayList<OfferSer>();

		try {
			for (int i = 0; i < offers.size(); i++) {
				OfferSer offerSer = new OfferSer();
				offerSer.setId(offers.get(i).getId());
				offerSer.setName(offers.get(i).getName());
				offerSer.setProductName(offers.get(i).getProductName());
				offerSer.setPlaceId(offers.get(i).getPlace().getId());
				// NTR
				// offerSer.setImgPath(UbiConstants.OFFER_IMAGES+offers.get(i).getImgPath()+".png");
				offerSer.setDescription(offers.get(i).getDescription());
				if (offers.get(i).getImgFile() != null) {
					offerSer.setImgURL(offers.get(i).getId() + "");
				} else {
					offerSer.setImgURL("");
				}

				offerSer.setValidFrom(DateUtil.getStringFromDate(offers.get(i).getValidFrom()));
				offerSer.setValidity(DateUtil.getStringFromDate(offers.get(i).getValidity()));
				Mapfavourite mapfavourite = null;
				if (userId > 0)
					mapfavourite = findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(userId,
							offers.get(i).getId());
				if (mapfavourite != null)
					offerSer.setTracked(mapfavourite.isIsActive());
				offfersSer.add(offerSer);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(out, offfersSer);

		final byte[] data = out.toByteArray();
		// System.out.println(new String(data));
		return new String(data);
	}

	public String writeTrackedOfferListToJsonArray(List<Offer> offers, int userId) throws IOException {
		List<OfferSer> offfersSer = new ArrayList<OfferSer>();

		for (int i = 0; i < offers.size(); i++) {
			OfferSer offerSer = new OfferSer();
			offerSer.setId(offers.get(i).getId());
			offerSer.setName(offers.get(i).getName());
			offerSer.setProductName(offers.get(i).getProductName());
			// offerSer.setPlaceId(offers.get(i).getPlace().getId());
			// NTR
			// offerSer.setImgPath(UbiConstants.OFFER_IMAGES+offers.get(i).getImgPath()+".png");
			// offerSer.setImgFile(offers.get(i).getImgFile());
			offerSer.setImgURL("" + offers.get(i).getId());

			// offerSer.setValidFrom(DateUtil.getStringFromDate(offers.get(i).getValidFrom()));
			// offerSer.setValidity(DateUtil.getStringFromDate(offers.get(i).getValidity()));
			Mapfavourite mapfavourite = null;
			// mapfavourite=findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(userId,offers.get(i).getId());

			if (mapfavourite != null && mapfavourite.isIsActive()) {
				offerSer.setTracked(mapfavourite.isIsActive());
				offfersSer.add(offerSer);
			}

		}
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(out, offfersSer);

		final byte[] data = out.toByteArray();
		// System.out.println(new String(data));
		return new String(data);
	}

	private Mapfavourite findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(int userId, int offerId) {
		Mapfavourite mapfavourite = new Mapfavourite();
		User trackedByUser = new User();
		trackedByUser.setId(userId);
		mapfavourite.setUser(trackedByUser);
		Favoritetype favoritetype = new Favoritetype();
		favoritetype.setId(1);
		mapfavourite.setFavoritetype(favoritetype);
		mapfavourite.setMapFavouriteEntityId(offerId);

		return mapfavouriteService.findByUserAndFavoritetypeAndMapFavouriteEntityId(trackedByUser, favoritetype,
				offerId);

	}

	public static class OfferSer implements Serializable {
		// Json format{"name":"Mufti","categoryId":"2","validity":"2014-02-10
		// 12:13","productName":"Mufti","description":"This is special offer"}
		private int id;
		private String name;
		private int placeId;
		private String placeLogoImg = "";
		private String productCatImg;
		private String validity;
		private String validFrom;
		private int categoryId;
		private String productName;
		private String description;
		private boolean isTracked;
		private String imgURL = "";
		private byte[] imgFile;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValidity() {
			return validity;
		}

		public void setValidity(String validity) {
			this.validity = validity;
		}

		public String getValidFrom() {
			return validFrom;
		}

		public void setValidFrom(String validFrom) {
			this.validFrom = validFrom;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public String getProductName() {
			return this.productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean getTracked() {
			return this.isTracked;
		}

		public void setTracked(boolean tracked) {
			this.isTracked = tracked;
		}

		public byte[] getImgFile() {
			return imgFile;
		}

		public void setImgFile(byte[] imgFile) {
			this.imgFile = imgFile;
		}

		public String getImgURL() {
			return imgURL;
		}

		public void setImgURL(String imgURL) {
			this.imgURL = imgURL;
		}

		public int getPlaceId() {
			return this.placeId;
		}

		public void setPlaceId(int placeId) {
			this.placeId = placeId;
		}

		public String getPlaceLogoImg() {
			return placeLogoImg;
		}

		public void setPlaceLogoImg(String placeLogoImg) {
			this.placeLogoImg = placeLogoImg;
		}

		public String getProductCatImg() {
			return productCatImg;
		}

		public void setProductCatImg(String productCatImg) {
			this.productCatImg = productCatImg;
		}

	}
}
