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
import com.ubilink.model.Productcategory;
import com.ubilink.service.ProductCategoryService;

@Controller
@RequestMapping(value="/requestProductCategory")
public class ProductCategoryController 
{
	private static final Logger logger = Logger.getLogger(ProductCategoryController.class); 
	
	@Autowired
	public ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
    public ResponseEntity<String> getProductCategories( )
    {
		logger.debug("requested getProductCategories()");
		List<Productcategory> productCategories=null;
		String result="failed";
    	try
    	{
    		productCategories=productCategoryService.findAll();
    		result=writeListToJsonArray(productCategories);
		} catch (Exception e) {
			logger.error("Exception in getProductCategories ");
			e.printStackTrace();
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
    	logger.info("Result of getProductCategories : "+result);
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }
	
	public String writeListToJsonArray(List<Productcategory>productcategories) throws IOException {  
		   
		List<ProductCategory>productCategories=new ArrayList<ProductCategory>();
			
		    for(int i=0;i<productcategories.size();i++)
		    {
		    	ProductCategory productCategory=new ProductCategory();
		    	Productcategory productcategory2=(Productcategory)productcategories.get(i);
		    	productCategory.setId(productcategory2.getId());
		    	productCategory.setCategory(productcategory2.getCategory());
		    	//NTR
		    	//productCategory.setImpPath(UbiConstants.PRODUCT_CATEGORY_IMAGES+productcategories.get(i).getImpPath()+".png");
		    	productCategories.add(productCategory);
		    }
		    final ByteArrayOutputStream out = new ByteArrayOutputStream();
		    final ObjectMapper mapper = new ObjectMapper();

		    mapper.writeValue(out, productCategories);

		    final byte[] data = out.toByteArray();
		    //System.out.println(new String(data));
		    return new String(data);
		}
	
	public static class ProductCategory implements Serializable
	{
		private static final long serialVersionUID = 1L;
		private int id;
		private String category;
		private String impPath;
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
		public String getImpPath() {
			return this.impPath;
		}
		public void setImpPath(String impPath) {
			this.impPath = impPath;
		}
		
	}
	
}
