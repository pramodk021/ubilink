/*
  package com.ubilink.controller;
  
  import java.util.List; import java.util.Map;
  
  import javax.servlet.http.HttpServletRequest; import
  javax.servlet.http.HttpServletResponse;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.MediaType; import
  org.springframework.stereotype.Controller; import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RequestMethod; import
  org.springframework.web.bind.annotation.ResponseBody;
  
  import com.ubilink.service.GenericService;
  
  @Controller
  
  @RequestMapping(value="/entity") public class GenericController<T> {
  
  @Autowired private GenericService genericService; private static final String
  ERROR_MESSAGE="message"; private static final String
  REPO_NOT_FOUND="Incorrect entity name."; private static final String
  METHOD_NOT_FOUND="Incorrect parameter details."; private static final String
  INTERNAL_SERVER_ERROR="Internal server error."; private static final String
  DATA="data";
  
  
  @RequestMapping( value="{name}",method= RequestMethod.GET)
  public @ResponseBody List<T> getEntityList(
  
  @PathVariable(value="name")String name,
  
  HttpServletRequest httpServletRequest, HttpServletResponse
  httpServletResponse ) { List<T>list=null;
  System.out.println(httpServletRequest.getQueryString());
  
  try { genericService.setQueryString(httpServletRequest.getQueryString());
  //GenericService<T>genericService=new
  GenericService<T>(httpServletRequest.getQueryString());
  if(!genericService.setGenericRepo(name)) {
  httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND); return list; }
  list=(List<T>) genericService.listAll(); if(list==null || list.size()==0) {
  httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,METHOD_NOT_FOUND); return list; }
  else httpServletResponse.setStatus(HttpStatus.OK.value());
  
  } catch (Exception e) {
  httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
  e.printStackTrace(); } return list; }
  
  
  @RequestMapping( value="/{name}/{id}",method= RequestMethod.GET)
  public @ResponseBody T getEntity(
  
  @PathVariable(value="name")String name,
  
  @PathVariable(value="id")int id, HttpServletRequest httpServletRequest,
  HttpServletResponse httpServletResponse ) { T entity=null;
  
  try { genericService.setQueryString(httpServletRequest.getQueryString());
  //GenericService<T>genericService=new
  GenericService<T>(httpServletRequest.getQueryString());
  if(!genericService.setGenericRepo(name)) {
  httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND); return null; }
  entity= (T) genericService.get(id); if(entity==null) {
  httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,METHOD_NOT_FOUND); return null; }
  else httpServletResponse.setStatus(HttpStatus.OK.value());
  
  } catch (Exception e) {
  httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
  e.printStackTrace(); } return entity; }
  
  @RequestMapping(value="/{name}",method=RequestMethod.POST,
  consumes={MediaType.APPLICATION_JSON_VALUE}) public @ResponseBody T
  create(@org.springframework.web.bind.annotation.RequestBody String json,
  
  @PathVariable(value="name")String name, HttpServletResponse
  httpServletResponse ) {
  
  Map<String,Object>map=null; try { if(!genericService.setGenericRepo(name) ||
  !genericService.setModel(name)) {
  httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND); return null; }
  map=genericService.create(json); HttpStatus
  httpStatus=(HttpStatus)map.get("status"); if(httpStatus.value()==201) {
  httpServletResponse.setStatus(httpStatus.value()); return (T)
  map.get("created"); } } catch(Exception exception) {
  httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,INTERNAL_SERVER_ERROR);
  exception.printStackTrace(); } return null; }
  
  @RequestMapping(value="/{name}/{id}", method=RequestMethod.DELETE) public
  
  @ResponseBody void delete(
  
  @PathVariable int id,
  
  @PathVariable String name, HttpServletResponse httpServletResponse ) { try {
  if(!genericService.setGenericRepo(name)) {
  httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
  httpServletResponse.setHeader(ERROR_MESSAGE,REPO_NOT_FOUND); } else {
  Map<String,Object>map= genericService.delete(id); HttpStatus
  httpStatus=(HttpStatus) map.get("status"); if(httpStatus!=null &&
  httpStatus.value()==204) { httpServletResponse.setStatus(httpStatus.value());
  } else if(httpStatus!=null && httpStatus.value()==404) {
  httpServletResponse.setStatus(httpStatus.value()); } } } catch(Exception
  exception) {
  httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
  
  }
  
  }
  
  }
*/ 