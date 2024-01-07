package com.ubilink.controller;


import java.io.ByteArrayOutputStream;
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
import com.ubilink.model.Ubiterms;
import com.ubilink.service.UbiTermService;

@Controller
@RequestMapping(value="/Ubiterms")
public class UbiTermsController 
{
	private static final Logger logger=Logger.getLogger(UbiTermsController.class);
	
	@Autowired
	public UbiTermService ubiTermService;
	
	@RequestMapping(value="/UbiLinkTerminologies",method=RequestMethod.GET)
    public ResponseEntity<String> getUbiLinkTerminologies() {
		logger.debug("requested getUbiLinkTerminologies()");
		List<Ubiterms> ubiterms=null;
		String result="failed";
    	try {
    		ubiterms=ubiTermService.getUbiTerms();
    		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		    final ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(out, ubiterms);
		    final byte[] data = out.toByteArray();
		    result=new String(data);
    		
		} catch (Exception e) {
			logger.error("Exception in getUbiLinkTerminologies :- "+e.getMessage());
			e.printStackTrace();
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
    	logger.debug("Result of getUbiLinkTerminologies : "+result);
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    } 
}
