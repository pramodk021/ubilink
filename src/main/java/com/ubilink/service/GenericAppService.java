/*package com.ubilink.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
@Service
public class GenericAppService<T>{
	
private Logger logger = Logger.getLogger(GenericService.class);
    
	@Autowired
	public ApplicationContext applicationContext;
	@Autowired
	public com.ubilink.gservice.GenericService<T> genericService;
	private T model;
	private Class[] paramTypes;
	private Object[] params;
	private String queryString;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public GenericAppService() {

	}
	@Transactional(readOnly=true)
	public List<T> listAll() {

		List<T> lists = null;
		try {
			Method method =null;
			
			if(queryString==null || queryString.equals(""))
			{
				method=genericService.getClass().getMethod("findAll",null);
				return (List<T>) method.invoke(genericService);
			}
			method= buildMethodName(null);
			 if (method != null) 
			{
				
				System.out.println("Method name = "+method.getName());
				if (method.getParameterTypes().length > 0)
					
				{
					lists = (List<T>) method.invoke(genericService,
							this.getParams());
				}
				else
				{
					lists = (List<T>) method.invoke(genericService);
					Hibernate.initialize(lists);
				}
			}
			
			// logger.debug("list of entity "+lists.getClass()+" :="+lists.toString());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lists;
	}

	
    public Map<String, Object> create( String json) {
	  System.out.println("repository class name = "+genericService.getClass().getName()+" Entity class name = "+json.getClass().getName());
	  Map<String, Object> m=Maps.newHashMap();
		  try 
		  {
			  ObjectMapper mapper=new ObjectMapper();
			  T object=(T) mapper.readValue(json.toString(), getModel().getClass());
			  if(object==null)
			  {
				  m.put("status", HttpStatus.BAD_REQUEST);
				  return m;
			  }
			  T created =genericService.saveAndFlush(object);
			  if(created!=null)
			  {
				  m.put("status", HttpStatus.CREATED);
				  m.put("created", created); 
			  }
			  else
				  m.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			  return m;
		  } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (JsonParseException e) {
			e.printStackTrace();
			m.put("status", HttpStatus.BAD_REQUEST.value());
			  return m;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			m.put("status", HttpStatus.BAD_REQUEST.value());
			  return m;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return m; 
	  }
	 
	public T get(int id) 
	{
		try {
			Method method =null;
			
			if(queryString==null || queryString.equals(""))
			{
			
				for(Method m:genericService.getClass().getMethods())
				{
					System.out.println(m.getName());
				}
				method=genericService.getClass().getMethod("findOne",int.class);
				return  (T) method.invoke(genericService,id);
			}
			method= buildMethodName("One");
			 if (method != null) 
			{
				
				System.out.println("Method name = "+method.getName());
				if (method.getParameterTypes().length > 0)
					return (T) method.invoke(genericService,
							this.getParams());
				else
					return (T) method.invoke(genericService);
			}
			
			// logger.debug("list of entity "+lists.getClass()+" :="+lists.toString());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @RequestMapping(value="/{id}", method=RequestMethod.POST,
	 * consumes={MediaType.APPLICATION_JSON_VALUE}) public @ResponseBody
	 * Map<String, Object> update(@PathVariable ID id, @RequestBody T json) {
	 * //logger.debug("update() of id#{} with body {}", id, json);
	 * //logger.debug("T json is of type {}", json.getClass());
	 * 
	 * T entity = this.repo.findOne(id); try { BeanUtils.copyProperties(entity,
	 * json); } catch (Exception e) { logger.warn("while copying properties",
	 * e); throw Throwables.propagate(e); }
	 * 
	 * // logger.debug("merged entity: {}", entity);
	 * 
	 * T updated = this.repo.save(entity); //
	 * logger.debug("updated enitity: {}", updated);
	 * 
	 * Map<String, Object> m = Maps.newHashMap(); m.put("success", true);
	 * m.put("id", id); m.put("updated", updated); return m; }
	
	  public Map<String, Object> delete(int id) 
	  {
		  Map<String, Object> m = Maps.newHashMap();
		  try {
			
			  genericService.delete(id);
			   m.put("status", HttpStatus.NO_CONTENT);
			  return m;
			} catch (SecurityException e) {
				
				e.printStackTrace();
				m.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (IllegalArgumentException e)
			{
				e.printStackTrace();
				m.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		    catch (EmptyResultDataAccessException e)
		    {
		    	e.printStackTrace();
				m.put("status", HttpStatus.NOT_FOUND);
			}
			return m;
	  }
	 
	  public long getCount()
	  {
		  try {
				Method method =null;
				
				if(queryString==null || queryString.equals(""))
				{
					return  genericService.count();
				}
				method= buildMethodName("Count");
				 if (method != null) 
				{
					
					System.out.println("Method name = "+method.getName());
					if (method.getParameterTypes().length > 0)
						return (long) method.invoke(genericService,
								this.getParams());
					else
						return (long) method.invoke(genericService);
				}
				
				// logger.debug("list of entity "+lists.getClass()+" :="+lists.toString());
			} catch (SecurityException e) {
				e.printStackTrace();
				return -1;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return -1;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return -1;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return -1;
			} 
			return -1;
	  }
	  
	private Method buildMethodName(String methodType) {

		Method qualifierMethod = null;
		try
		{
			
		
			for (Method method : genericService.getClass().getMethods()) {
				if(methodType!=null && !method.getName().contains(methodType))
				{
					continue;
				}
				String methodName = findMethodByParams(method);
				if (!methodName.equals("")) {
					return method;
				}
			}
	
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return qualifierMethod;
	}

	private String findMethodByParams(Method method) {
		try {
			boolean isDesiredMethod = true;

			String methodName = method.getName().replace("find", "")
					.replace("By", "").replace("And", "").replace("Or", "")
					.replace("Page", "").replace("List", "").replace("Count", "").replace("get", "");
			Map<Integer, Object> paramValues = new TreeMap<Integer, Object>();
			String[] params = queryString != null ? queryString.split("&")
					: new String[0];
			for (String paramKeyValues : params) {
				String paramKey = paramKeyValues.split("=").length > 1 ? paramKeyValues
						.split("=")[0] : "";
				String paramValue = paramKeyValues.split("=").length > 1 ? paramKeyValues
						.split("=")[1] : "";
				if (methodName.contains(paramKey)) {
					if (isNumeric(paramValue)) {
						int val = Integer.parseInt(paramValue);
						paramValues.put(methodName.indexOf(paramKey), val);
					} else
						paramValues.put(methodName.indexOf(paramKey),
								paramValue);
				} else if (!paramKey.equals("pageNum")
						&& !paramKey.equals("pageSize")) {
					isDesiredMethod = false;
					break;
				}

			}
			if (isDesiredMethod) {
				setParamTypes(method.getParameterTypes());
				if (method.getName().contains("Page")) {
					int pageNum = 0;
					int pageSize = 5;
					for (String paramValue : params) {
						if (paramValue.contains("pageNum")) {
							pageNum = paramValue.split("=").length > 1 ? Integer
									.parseInt(paramValue.split("=")[1]) : 0;
						}
						if (paramValue.contains("pageSize")) {
							pageSize = paramValue.split("=").length > 1 ? Integer
									.parseInt(paramValue.split("=")[1]) : 5;
						}
					}
					paramValues.put(method.getName().length(), new PageRequest(
							pageNum, pageSize));
					
					this.setParams(paramValues.values().toArray());
				} else {
					this.setParams(paramValues.values().toArray());
				}
				return method.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

	public Object[] getParams() {
		return this.params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Class[] getParamTypes() {
		return this.paramTypes;
	}

	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public static boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

	public boolean setGenericRepo(String repoName) {
		try
		{
		
			this.genericService = getRepository(repoName);
			if(genericService!=null)
				return true;
			else 
				return false;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	private com.ubilink.gservice.GenericService<T> getRepository(String name) 
	{
		try{
			return  (com.ubilink.gservice.GenericService<T>) applicationContext.getBean(name+ "ServiceGImpl");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	public T getModel() {
		return this.model;
	}

	public boolean setModel(String  modelName) 
	{
		try{
			this.model = (T) applicationContext.getBean(modelName);
			if(model==null)
				return false;
			else
				return true;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

}
 */