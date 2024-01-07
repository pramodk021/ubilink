/*
package com.ubilink.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ubilink.service.GenericAppService;

@Controller
@RequestMapping(value="/app")
public class GenericAppController<T>
{
	@Autowired
	public GenericAppService<T> genericAppService;
	private static final String ERROR_MESSAGE="message";
	private static final String REPO_NOT_FOUND="Incorrect entity name.";
	private static final String METHOD_NOT_FOUND="Incorrect parameter details.";
	private static final String INTERNAL_SERVER_ERROR="Internal server error.";
	
	@RequestMapping( value="{name}",method= RequestMethod.GET)
	public @ResponseBody T getEntityList(@PathVariable(value="name") String name,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		List<T>list=null;
		System.out.println(httpServletRequest.getQueryString());
		try
		{
			genericAppService.setQueryString(httpServletRequest.getQueryString());
			//GenericService<T>genericService=new GenericService<T>(httpServletRequest.getQueryString());
			if(!genericAppService.setGenericRepo(name))
			{
				httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND);
				return null;
			}
			list=(List<T>) genericAppService.listAll();
			if(list==null)
			{
				httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				httpServletResponse.setHeader(ERROR_MESSAGE,METHOD_NOT_FOUND);
				return null;
			}
			else
			{
				httpServletResponse.setStatus(HttpStatus.OK.value());
				T listEnity=(T)list;
				return (T) convertJavaToJsonString(listEnity);
			}
			
		}  catch (Exception e) 
		{
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping( value="/{name}/{id}",method= RequestMethod.GET)
	public ResponseEntity<String> getEntity(@PathVariable(value="name")String name,@PathVariable(value="id")int id,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		T entity=null;
		try
		{
			genericAppService.setQueryString(httpServletRequest.getQueryString());
			//GenericService<T>genericService=new GenericService<T>(httpServletRequest.getQueryString());
			if(!genericAppService.setGenericRepo(name))
			{
				httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND);
				return null;
			}
			entity= (T) genericAppService.get(id);
			if(entity==null)
			{
				httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				httpServletResponse.setHeader(ERROR_MESSAGE,METHOD_NOT_FOUND);
				return null;
			}
			else
				httpServletResponse.setStatus(HttpStatus.OK.value());
			
		}  catch (Exception e) 
		{
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		String jsonString=convertJavaToJsonString(entity);
		return new ResponseEntity<String>(jsonString,responseHeaders,HttpStatus.OK);
	}
	
	 @RequestMapping(value="/{name}",method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}) 
	 public @ResponseBody T create(@org.springframework.web.bind.annotation.RequestBody String json,
					  @PathVariable(value="name")String name,
					  HttpServletResponse httpServletResponse
					  ) 
	 {

		 Map<String,Object>map=null;
		 try
		 {
			 if(!genericAppService.setGenericRepo(name) || !genericAppService.setModel(name))
			 {
				 httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				 httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND);
				 return null;
			 }
			 map=genericAppService.create(json);
			 HttpStatus httpStatus=(HttpStatus)map.get("status");
			 if(httpStatus.value()==201)
			 {
				 httpServletResponse.setStatus(httpStatus.value());
				 T createdEnity=(T) map.get("created");
				return (T) convertJavaToJsonString(createdEnity );
			 }
		 }
		 catch(Exception exception)
		 {
			 httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			 httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
			 exception.printStackTrace();
		 }
	     return  null;
	 }
	
	 @RequestMapping(value="/{name}/{id}", method=RequestMethod.DELETE)
	 public @ResponseBody void delete(@PathVariable int id,@PathVariable String name,HttpServletResponse httpServletResponse) {
		 try
		 {
			 if(!genericAppService.setGenericRepo(name))
			 {
				 httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				 httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND);
			 }
			 else
			 {
				 Map<String,Object>map= genericAppService.delete(id);
				 HttpStatus httpStatus=(HttpStatus) map.get("status");
				 if(httpStatus!=null && httpStatus.value()==204)
				 {
					 httpServletResponse.setStatus(httpStatus.value());
				 }
				 else if(httpStatus!=null && httpStatus.value()==404)
				 {
					 httpServletResponse.setStatus(httpStatus.value());
				 }
			 }
		 }
		 catch(Exception exception)
		 {
			 httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		 }
	    
	  }
	 
	 @RequestMapping(value="/{name}/count",method=RequestMethod.GET) 
	 public @ResponseBody long getCount(@PathVariable(value="name")String name,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		 long count=0;
		 try
		 {
			 genericAppService.setQueryString(httpServletRequest.getQueryString());
			 if(!genericAppService.setGenericRepo(name) )
			 {
				 httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
				 httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND);
				 return -1;
			 }
			 count=genericAppService.getCount();
			 if(count>-1)
			 {
				 httpServletResponse.setStatus(HttpStatus.OK.value());
				return count;
			 }
			 else
			 {
				 httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				 httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
				 return -1;
			 }
		 }
		 catch(Exception exception)
		 {
			 httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			 httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
			 exception.printStackTrace();
		 }
	     return  -1;
	 }
	 
	 
	 
	 public String convertJavaToJsonString(T javaEntity)
	 {
		 ObjectMapper objectMapper = new ObjectMapper();
			String jsonString=null;
			try {
				//jsonString=  objectMapper.writeValueAsString(entity);
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
				objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
				objectMapper.writeValue(out, javaEntity);

			    final byte[] data = out.toByteArray();
			    jsonString=new String(data);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jsonString;
	 }
	
}
*/