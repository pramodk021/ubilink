package com.ubilink.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubilink.model.Placecategory;
import com.ubilink.service.PlaceCatService;

@Controller
@RequestMapping(value = "/requestPlaceCategory")
public class PlaceCategoryController {
	private static final Logger logger = Logger.getLogger(PlaceCategoryController.class);
	@Autowired
	public PlaceCatService placeCatService;
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ResponseEntity<String> getPlaceCategories() {
		logger.debug("requested getProductCategories()");
		List<Placecategory> placecategories = null;
		String result = "failed";
		try {
			placecategories = placeCatService.findValidCategories();
			result = writeListToJsonArray(placecategories);
		} catch (Exception e) {
			logger.error("Exception in getProductCategories ");
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Cache-Control", "private");
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
		logger.info("Result of getPlaceCategories : " + result);
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
	}

	public String writeListToJsonArray(List<Placecategory> placecategories) throws IOException {
		List<PlaceCategory> placecategories2 = new ArrayList<PlaceCategory>();

		for (int i = 0; i < placecategories.size(); i++) {
			PlaceCategory placecategory = new PlaceCategory();
			placecategory.setId(placecategories.get(i).getId());
			// placecategory.setCategory(placecategories.get(i).getCategory());
			placecategory.setCategory(placecategories.get(i).getDisplayCategory());
			if (placecategories.get(i).getCategoryImg() != null)
				placecategory.setCatImg("" + placecategories.get(i).getId());

			placecategories2.add(placecategory);
		}
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(out, placecategories2);

		final byte[] data = out.toByteArray();
		return new String(data);
	}

	public static class PlaceCategory implements Serializable {
		private int id;
		private String category;
		private String catImg = "";

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCategory() {
			return this.category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getCatImg() {
			return this.catImg;
		}

		public void setCatImg(String catImg) {
			this.catImg = catImg;
		}

	}

}
